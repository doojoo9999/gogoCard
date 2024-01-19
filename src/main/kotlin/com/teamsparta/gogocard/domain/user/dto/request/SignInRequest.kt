package com.teamsparta.gogocard.domain.user.dto.request

data class SignInRequest (
//    val id: Long,
    val password: String,
    val email: String,
    val role : String,
)