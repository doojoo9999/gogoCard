package com.teamsparta.gogocard.domain.gogocard.dto

import java.time.LocalDateTime

data class CardResponse(
    val id: Long,
    val title: String,
    val content: String,
    val date: LocalDateTime,
    val author: String
)
