package com.teamsparta.gogocard.domain.gogocard.dto.request

data class UpdateCardRequest(
    val title: String,
    val content: String,
    val userId: Long,
    val complete: Boolean
)
