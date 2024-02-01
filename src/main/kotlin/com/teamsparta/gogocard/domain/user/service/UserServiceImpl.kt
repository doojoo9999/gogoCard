package com.teamsparta.gogocard.domain.user.service

import com.teamsparta.gogocard.domain.exception.InvalidCredentialsException
import com.teamsparta.gogocard.domain.exception.ModelNotFoundException
import com.teamsparta.gogocard.domain.user.dto.request.CreateUserRequest
import com.teamsparta.gogocard.domain.user.dto.request.SignInRequest
import com.teamsparta.gogocard.domain.user.dto.response.SendMailResponse
import com.teamsparta.gogocard.domain.user.dto.response.SignInResponse
import com.teamsparta.gogocard.domain.user.dto.response.UserResponse
import com.teamsparta.gogocard.domain.user.model.MailEntity
import com.teamsparta.gogocard.domain.user.model.UserEntity
import com.teamsparta.gogocard.domain.user.model.toResponse
import com.teamsparta.gogocard.domain.user.repository.MailRepository
import com.teamsparta.gogocard.domain.user.repository.UserRepository
import com.teamsparta.gogocard.domain.utility.MailUtility
import com.teamsparta.gogocard.infra.security.jwt.JwtPlugin
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin,
    private val mailRepository: MailRepository,
    private val mailUtility: MailUtility
) : UserService {

    @Transactional
    override fun signUp(request: CreateUserRequest): UserResponse {

        val getAuthCode = mailRepository.findAllByEmail(request.email)
            ?: throw ModelNotFoundException("Email", null)

        if (getAuthCode.none { it.authcode == request.authcode }) {
            throw IllegalStateException("인증 코드가 틀렸습니다.")
        }

        if (userRepository.existsByEmail(request.email)) {
            throw IllegalStateException("이미 존재하는 이메일입니다.")
        }

        mailRepository.deleteByEmail(request.email)

        return userRepository.save(
            UserEntity(
                userName = request.userName,
                email = request.email,
                password = passwordEncoder.encode(request.password),
                role = request.role)
        ).toResponse()
    }


//        if (userRepository.existsByEmail(request.email)) {
//            throw IllegalStateException("email is already in use")
//        }
//
//        return userRepository.save(
//            UserEntity(
//                email = request.email,
//                password = passwordEncoder.encode(request.password),
//                userName = request.userName,
//                role = when (request.role) {
//                    "MEMBER" -> UserRole.MEMBER
//                    "ADMIN" -> UserRole.ADMIN
//                    else -> throw IllegalArgumentException("Invalid Role")
//                }
//            )
//        ).toResponse()


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

//@Transactional 트랜잭션을 뺀 이유 : 이걸 해두면 같은 작업이 반복될 때 최초 1개 이메일에 대해서만 DB에 저장되었음.
    //아마 같은 요청이 반복되면 하나의 작업으로 인식하는 것 같음.

    override fun sendMail(email: String): SendMailResponse {

        val randomString = mailUtility.sendMail(email)

        mailRepository.save(
            MailEntity(
                email = email,
                authcode = randomString
            )
        )

        return SendMailResponse(message = "메일 발송 완료")


    //        //Utility로 뺌
//        //인증 번호 만들기
//        val length = 6
//        val randomString = getRandomString(length)
//
//        //이메일 발송하기
//        val message = javaMailSender.createMimeMessage()
//        val helper = MimeMessageHelper(message)
//        helper.setTo(email)
//        helper.setSubject("gogoCard 이메일 인증")
//        helper.setText("인증 코드 : $randomString")
//        helper.setFrom("doojoo0536@gmail.com")
//        javaMailSender.send(message)


}



}

