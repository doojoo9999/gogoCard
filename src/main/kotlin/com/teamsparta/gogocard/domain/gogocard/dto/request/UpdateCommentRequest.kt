package com.teamsparta.gogocard.domain.gogocard.dto.request

data class UpdateCommentRequest(
    val content: String,
    val userId: Long,
)