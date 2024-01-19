package com.teamsparta.gogocard.domain.user.service

import com.teamsparta.gogocard.domain.user.dto.request.CreateUserRequest
import com.teamsparta.gogocard.domain.user.dto.request.SignInRequest
import com.teamsparta.gogocard.domain.user.dto.response.SignInResponse
import com.teamsparta.gogocard.domain.user.dto.response.UserResponse

interface UserService {

    fun signUp(request: CreateUserRequest) : UserResponse

    fun signIn(request: SignInRequest) : SignInResponse

}