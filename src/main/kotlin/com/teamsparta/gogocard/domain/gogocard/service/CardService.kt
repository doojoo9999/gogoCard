package com.teamsparta.gogocard.domain.gogocard.service

import com.teamsparta.gogocard.domain.gogocard.dto.*

interface CardService {

    fun getCardList() : List<CardResponse>

    fun getCardById(cardId: Long) : CardResponse

    fun createCard(request: CreateCardRequest) : CardResponse

    fun updateCard(cardId:Long, request: UpdateCardRequest) : CardResponse

    fun deleteCard(cardId:Long)

    fun createComment(cardId:Long, request: CreateCommentRequest) : CommentResponse

    fun updateComment(cardId:Long, commentId: Long, request: UpdateCommentRequest) : CommentResponse

    fun deleteComment(cardId:Long, commentId: Long, request: DeleteCommentRequest) : CommentResponse
}
