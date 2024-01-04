package com.teamsparta.gogocard.domain.gogocard.service

import com.teamsparta.gogocard.domain.exception.ModelNotFoundException
import com.teamsparta.gogocard.domain.exception.ModelNotMatchException
import com.teamsparta.gogocard.domain.gogocard.dto.*
import com.teamsparta.gogocard.domain.gogocard.model.CardEntity
import com.teamsparta.gogocard.domain.gogocard.model.CommentEntity
import com.teamsparta.gogocard.domain.gogocard.model.toResponse
import com.teamsparta.gogocard.domain.gogocard.model.toResponseWithComments
import com.teamsparta.gogocard.domain.gogocard.repository.CardRepository
import com.teamsparta.gogocard.domain.gogocard.repository.CommentRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CardServiceImpl(
    private val cardRepository: CardRepository,
    private val commentRepository: CommentRepository
) : CardService {

    override fun getCardList(): List<CardResponse> {
        return cardRepository.findAll().map { it.toResponse() }
    }

    override fun getCardById(cardId:Long): CardResponse {
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException ("Card", cardId)
        return card.toResponseWithComments()
    }
    @Transactional
    override fun createCard(request: CreateCardRequest): CardResponse {
        return cardRepository.save(
            CardEntity(
                title = request.title,
                content = request.content,
                author = request.author,
                date = request.date,
                complete = false
            )
        ).toResponse()
    }

    @Transactional
    override fun updateCard(cardId:Long, request: UpdateCardRequest): CardResponse {
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException ("Card", cardId)
        val (title, content, author, complete) = request

        card.title = title
        card.content = content
        card.author = author
        card.complete = complete

        return cardRepository.save(card).toResponse()

//        TODO("db에 저장된 할 일 카드를 불러온 뒤, 원하는 값으로 수정하여 db에 CardResponse 형태로 저장한다.")
    }

    @Transactional
    override fun deleteCard(cardId:Long) {
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException ("Card", cardId)
        cardRepository.delete(card)


//        TODO("요청된 cardId와 일치하는 카드를 db에서 확인한 뒤, 해당 card를 db에서 삭제한다.")
    }

    override fun createComment(
        cardId: Long,
        request: CreateCommentRequest
    ): CommentResponse {
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException ("card", cardId)
        val comment = CommentEntity(
            content = request.content,
            password = request.password,
            author = request.author,
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
        if (comment.author != request.author || comment.password != request.password) {
            throw ModelNotMatchException("author or password", request.author, request.password)
        }
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
        if (comment.author != request.author || comment.password != request.password) {
            throw ModelNotMatchException("author or password", request.author, request.password)
        }
        commentRepository.delete(comment)

        return commentRepository.save(comment).toResponse()
    }

}