package com.teamsparta.gogocard.domain.user.dto.request

data class CreateUserRequest (
    val userName : String,
    val email : String,
    val password : String,
    val role : String,
)