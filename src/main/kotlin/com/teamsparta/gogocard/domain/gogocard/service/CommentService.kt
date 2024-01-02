package com.teamsparta.gogocard.domain.gogocard.service

import com.teamsparta.gogocard.domain.gogocard.dto.*

interface CommentService {

    fun createComment(request: CreateCommentRequest) : CommentResponse

    fun updateComment(cardId: Long, commentId: Long) : CommentResponse

    fun deleteComment(cardId: Long, commentId: Long)



    fun createCard(request: CreateCardRequest) : CardResponse

    fun updateCard(cardId:Long, request: UpdateCardRequest) : CardResponse

    fun deleteCard(cardId:Long)

}