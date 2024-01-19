package com.teamsparta.gogocard.domain.gogocard.dto

import com.teamsparta.gogocard.domain.gogocard.model.Complete
import java.time.LocalDateTime

data class CardResponse(
    val id: Long,
    val title: String,
    val content: String,
    val date: LocalDateTime?,
    val userName: String,
    val complete: Boolean,
    val comments : List<CommentResponse> ?= null
)
