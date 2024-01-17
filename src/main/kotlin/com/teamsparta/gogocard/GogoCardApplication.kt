package com.teamsparta.gogocard

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@EnableAspectJAutoProxy
@SpringBootApplication
class GogoCardApplication

fun main(args: Array<String>) {
    runApplication<GogoCardApplication>(*args)
}
