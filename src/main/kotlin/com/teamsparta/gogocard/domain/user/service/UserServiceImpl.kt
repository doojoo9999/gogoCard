package com.teamsparta.gogocard.domain.user.service

import com.teamsparta.gogocard.domain.exception.InvalidCredentialsException
import com.teamsparta.gogocard.domain.exception.ModelNotFoundException
import com.teamsparta.gogocard.domain.user.dto.request.CreateUserRequest
import com.teamsparta.gogocard.domain.user.dto.request.SendMailRequest
import com.teamsparta.gogocard.domain.user.dto.request.SignInRequest
import com.teamsparta.gogocard.domain.user.dto.response.SendMailResponse
import com.teamsparta.gogocard.domain.user.dto.response.SignInResponse
import com.teamsparta.gogocard.domain.user.dto.response.UserResponse
import com.teamsparta.gogocard.domain.user.model.*
import com.teamsparta.gogocard.domain.user.repository.MailRepository
import com.teamsparta.gogocard.domain.user.repository.UserRepository
import com.teamsparta.gogocard.infra.security.jwt.JwtPlugin
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin,
    private val javaMailSender : JavaMailSender,
    private val mailRepository: MailRepository
) : UserService {

    @Transactional
    override fun signUp(request: CreateUserRequest): UserResponse {
        if (userRepository.existsByEmail(request.email)) {
            throw IllegalStateException("email is already in use")
        }

        return userRepository.save(
            UserEntity(
                email = request.email,
                password = passwordEncoder.encode(request.password),
                userName = request.userName,
                role = when (request.role) {
                    "MEMBER" -> UserRole.MEMBER
                    "ADMIN" -> UserRole.ADMIN
                    else -> throw IllegalArgumentException("Invalid Role")
                }
            )
        ).toResponse()

    }

    @Transactional
    override fun signIn(request: SignInRequest): SignInResponse {
        val user = userRepository.findByEmail(request.email) ?: throw ModelNotFoundException("User", null)

        if (user.role.name != request.role || !passwordEncoder.matches(request.password, user.password)) {
            throw InvalidCredentialsException()
        }
        return SignInResponse(
            accessToken = jwtPlugin.generateAccessToken(
                subject = user.id.toString(),
                email = user.email,
                role = user.role.name
            )
        )

    }

    @Transactional
    override fun sendMail(email : String): SendMailResponse {

        //인증 번호 만들기
        val length = 6
        val randomString = getRandomString(length)

        //이메일 발송하기
        val message = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(message)
        helper.setTo(email)
        helper.setSubject("gogoCard 이메일 인증")
        helper.setText("인증 코드 : $randomString")
        helper.setFrom("doojoo0536@gmail.com")
        javaMailSender.send(message)

        mailRepository.save(
            MailEntity(
                email = email,
                authcode = randomString
            )
        )


        return SendMailResponse("메일 발송 완료")

    }


}

