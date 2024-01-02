package com.teamsparta.gogocard.domain.gogocard.service

import com.teamsparta.gogocard.domain.gogocard.dto.*

class CommentServiceImpl(
    val commentService: CommentService
) : CommentService {
    override fun createComment(request: CreateCommentRequest): CommentResponse {
        TODO("Not yet implemented")
    }

    override fun updateComment(cardId: Long, commentId: Long): CommentResponse {
        TODO("Not yet implemented")
    }

    override fun deleteComment(cardId: Long, commentId: Long) {
        TODO("Not yet implemented")
    }

    override fun createCard(request: CreateCardRequest): CardResponse {
        TODO("Not yet implemented")
    }

    override fun updateCard(cardId: Long, request: UpdateCardRequest): CardResponse {
        TODO("Not yet implemented")
    }

    override fun deleteCard(cardId: Long) {
        TODO("Not yet implemented")
    }
}