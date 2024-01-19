package com.teamsparta.gogocard.domain.gogocard.dto

import io.swagger.v3.oas.models.media.Content

data class CommentResponse(
    val id: Long,
    val content: String,
    val userName: String
)
