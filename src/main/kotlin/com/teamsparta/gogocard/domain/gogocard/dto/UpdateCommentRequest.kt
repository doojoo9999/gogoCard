package com.teamsparta.gogocard.domain.gogocard.dto

data class UpdateCommentRequest(
    val content: String,
    val userName: String,
    val password: String
)