package com.teamsparta.gogocard.domain.gogocard.dto

data class CreateCommentRequest (
    val content: String,
    val password: String,
    val userId: Long
)