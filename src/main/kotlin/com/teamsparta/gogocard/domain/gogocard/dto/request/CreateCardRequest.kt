package com.teamsparta.gogocard.domain.gogocard.dto.request

import java.time.LocalDateTime

data class CreateCardRequest(
    val title: String,
    val content: String,
    val date: LocalDateTime,
    val userId: Long,
)
