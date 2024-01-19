package com.teamsparta.gogocard.domain.gogocard.service

import com.teamsparta.gogocard.domain.gogocard.dto.*
import com.teamsparta.gogocard.domain.gogocard.model.Complete
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CardService {

    fun getCardList() : List<CardResponse>

    fun getCardById(cardId: Long) : CardResponse

    fun createCard(request: CreateCardRequest) : CardResponse

    fun updateCard(cardId:Long, request: UpdateCardRequest) : CardResponse

    fun deleteCard(cardId:Long)

    fun createComment(cardId:Long, request: CreateCommentRequest) : CommentResponse

    fun updateComment(cardId:Long, commentId: Long, request: UpdateCommentRequest) : CommentResponse

    fun deleteComment(cardId:Long, commentId: Long, request: DeleteCommentRequest) : CommentResponse

    fun getCardByAuthor(cardId:Long, author: String, request: CallCardByAuthorRequest) : List<CardResponse>

    fun completeCard (cardId:Long) : CardResponse

    fun searchCardListWithTitle(title: String): List<CardResponse>
    fun searchCardListWIthComplete(isComplete : Boolean): List<CardResponse>
    fun getPaginatedCardList(pageable: Pageable, _isCompleted: Boolean?): Page<CardResponse>?


}
