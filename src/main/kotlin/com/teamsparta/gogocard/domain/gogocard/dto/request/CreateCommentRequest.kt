package com.teamsparta.gogocard.domain.gogocard.dto.request

data class CreateCommentRequest (
    val content: String,
    val password: String,
    val userId: Long
)