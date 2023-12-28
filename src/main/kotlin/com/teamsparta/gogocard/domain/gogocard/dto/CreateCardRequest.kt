package com.teamsparta.gogocard.domain.gogocard.dto

data class CreateCardRequest(
    val title: String,
    val content: String,
    val date: String,
    val author: String
)
