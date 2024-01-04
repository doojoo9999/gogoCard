package com.teamsparta.gogocard.domain.exception

data class ModelNotMatchException (
    val modelName: String, val author:String, val password:String): RuntimeException(
    "wrong name or password modelName: ${modelName}, author: ${author}, password: ${password}"
)