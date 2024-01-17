package com.teamsparta.gogocard.infra.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

//@Aspect
//@Component
class TestAop {

    @Around("execution(* com.teamsparta.gogocard.domain.gogocard.service.CardService.getCardById(..))")
    fun thisIsAdvice(joinPoint: ProceedingJoinPoint) {
        println("AOP START!!!")
        joinPoint.proceed()
        println("AOP END!!!")
    }

}