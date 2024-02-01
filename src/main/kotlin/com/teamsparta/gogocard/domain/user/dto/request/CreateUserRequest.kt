package com.teamsparta.gogocard.domain.user.dto.request

import com.teamsparta.gogocard.domain.user.model.UserRole

data class CreateUserRequest (
    val userName : String,
    val email : String,
    val authcode : String,
    val password : String,
    val role : UserRole,
)