package com.teamsparta.gogocard.domain.exception

data class ModelNotMatchException (
    val modelName: String, val userName:String, val password:String): RuntimeException(
    "wrong name or password modelName: ${modelName}, userName: ${userName}, password: ${password}"
)