package com.teamsparta.gogocard.domain.exception

data class InvalidCredentialsException (
    override val message: String? = "The credential is invalid"
) : RuntimeException()