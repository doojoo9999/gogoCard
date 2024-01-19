package com.teamsparta.gogocard.domain.gogocard.service

import com.teamsparta.gogocard.domain.exception.ModelNotFoundException
import com.teamsparta.gogocard.domain.exception.ModelNotMatchException
import com.teamsparta.gogocard.domain.gogocard.dto.*
import com.teamsparta.gogocard.domain.gogocard.model.*
import com.teamsparta.gogocard.domain.gogocard.repository.CardRepository
import com.teamsparta.gogocard.domain.gogocard.repository.CommentRepository
import com.teamsparta.gogocard.domain.gogocard.repository.CardRepositoryImpl
import com.teamsparta.gogocard.domain.user.repository.UserRepository
import com.teamsparta.gogocard.infra.aop.StopWatch
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CardServiceImpl(
    private val cardRepository: CardRepository,
    private val commentRepository: CommentRepository,
    private val userRepository: UserRepository,
    ) : CardService {

    override fun getCardList(): List<CardResponse> {
        return cardRepository.findAll().map { it.toResponse() }
    }

    @Transactional
    @StopWatch
    override fun getCardById(cardId:Long): CardResponse {
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException ("Card", cardId)
        return card.toResponseWithComments()
    }
    override fun createCard(request: CreateCardRequest): CardResponse {
        val user = userRepository.findByUserName(request.userName) ?: throw IllegalStateException ("User Not Found")
        return cardRepository.save(
            CardEntity(
                title = request.title,
                content = request.content,
                userName = user,
                date = request.date,
            )
        ).toResponse()
    }

    @Transactional
    override fun updateCard(cardId:Long, request: UpdateCardRequest): CardResponse {
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException ("Card", cardId)
        val user = userRepository.findByUserName(request.userName) ?: throw IllegalStateException ("User Not Found")
        val (title, content, userName) = request

        card.title = title
        card.content = content
        card.userName = user

        return cardRepository.save(card).toResponse()

    }

    @Transactional
    override fun deleteCard(cardId:Long) {
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException ("Card", cardId)
        cardRepository.delete(card)

    }

    override fun createComment(
        cardId: Long,
        request: CreateCommentRequest
    ): CommentResponse {
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException ("card", cardId)
        val user = userRepository.findByUserName(request.userName) ?: throw IllegalStateException ("User Not Found")
        val comment = CommentEntity(
            content = request.content,
            password = request.password,
            userName = user,
            card = card
        )
        return commentRepository.save(comment).toResponse()
    }

    override fun updateComment(
        cardId: Long,
        commentId: Long,
        request: UpdateCommentRequest
    ): CommentResponse {
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException("Card", cardId)
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("comment", commentId)
        val user = userRepository.findByUserName(request.userName) ?: throw IllegalStateException ("User Not Found")
//        if (comment.userName != user || comment.password != request.password) {
//            throw ModelNotMatchException("author or password", request.userName, request.password)
//        }
//        인가 기능 구현 헀으니, 검증 과정 삭제
        comment.content = request.content
        return commentRepository.save(comment).toResponse()
    }



    override fun deleteComment(
        cardId: Long,
        commentId: Long,
        request: DeleteCommentRequest
    ): CommentResponse {
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException("card", cardId)
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("comment", commentId)
//        if (comment.author != request.author || comment.password != request.password) {
//            throw ModelNotMatchException("author or password", request.author, request.password)
//        }
//        인가 기능 구현 했으니 검증 과정 삭제함
        commentRepository.delete(comment)

        return commentRepository.save(comment).toResponse()
    }

    override fun getCardByAuthor(
        cardId: Long,
        author: String,
        request: CallCardByAuthorRequest
    ): List<CardResponse> {
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException("card", cardId)
        val user = userRepository.findByUserName(request.userName) ?: throw IllegalStateException ("User Not Found")
        if (card.userName != user ) {
            throw ModelNotFoundException("author", cardId)
        }

        return cardRepository.findByAuthor(request.userName).map { it.toResponse() }

    }

    override fun completeCard(
        cardId: Long
    ) : CardResponse {
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException("card", cardId)

        card.complete()

        return cardRepository.save(card).toResponse()
    }

    override fun searchCardListWithTitle(title: String): List<CardResponse> {
        return cardRepository.searchCardListByTitle(title).map { it.toResponse() }
    }

    override fun searchCardListWIthComplete(isComplete: Boolean): List<CardResponse> {
        return cardRepository.searchCardListByComplete(isComplete).map { it.toResponse() }
    }

    override fun getPaginatedCardList(pageable: Pageable, _isCompleted: Boolean?): Page<CardResponse>? {
        val cardComplete = when (_isCompleted) {
            false -> Complete.NO_COMPLETE
            true -> Complete.COMPLETE
            null -> null
        }

        return cardRepository.findByPageableAndComplete(pageable, cardComplete).map { it.toResponse() }
    }
}