package com.teamsparta.gogocard.domain.user.controller

import com.teamsparta.gogocard.domain.user.dto.request.CreateUserRequest
import com.teamsparta.gogocard.domain.user.dto.request.SignInRequest
import com.teamsparta.gogocard.domain.user.dto.response.SignInResponse
import com.teamsparta.gogocard.domain.user.dto.response.UserResponse
import com.teamsparta.gogocard.domain.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/users")
@RestController
class UserController (
    private val userService: UserService
){

    @PostMapping("/signup")
    fun signUp(
        @RequestBody createUserRequest: CreateUserRequest
    ) : ResponseEntity<UserResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.signUp(createUserRequest))
    }

    @PostMapping("/signin")
    fun signIn(
        @RequestBody signInRequest: SignInRequest
    ) : ResponseEntity<SignInResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.signIn(signInRequest))

    }

}