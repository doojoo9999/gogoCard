package com.teamsparta.gogocard.domain.gogocard.dto.response

import java.time.LocalDateTime

data class CardResponse(
    val id: Long,
    val title: String,
    val content: String,
    val userId: Long,
    val complete: Boolean,
    val comments : List<CommentResponse> ?= null
)
