package com.teamsparta.gogocard.domain.utility

import com.teamsparta.gogocard.domain.user.model.MailEntity
import com.teamsparta.gogocard.domain.user.repository.MailRepository
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component

@Component
class MailUtility(
     val javaMailSender : JavaMailSender,
     val mailRepository: MailRepository,
) {

    fun getRandomString() : String {
        val length = 6
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }

    fun sendMail(email: String) : String {
        //인증 번호 만들기
        val randomString = getRandomString()

        //이메일 발송하기
        val message = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(message)
        helper.setTo(email)
        helper.setSubject("gogoCard 이메일 인증")
        helper.setText("인증 코드 : $randomString")
        helper.setFrom("doojoo0536@gmail.com")
        javaMailSender.send(message)


        return randomString

    }

}