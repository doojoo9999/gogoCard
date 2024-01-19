package com.teamsparta.gogocard.domain.gogocard.dto

data class UpdateCardRequest(
    val title: String,
    val content: String,
    val userName: String,
    val complete: Boolean
)
