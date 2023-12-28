package com.teamsparta.gogocard.domain.gogocard.dto

data class CardResponse(
    val id: Long,
    val title: String,
    val content: String,
    val date: String,
    val author: String
)
