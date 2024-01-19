package com.teamsparta.gogocard.domain.user.service

import com.teamsparta.gogocard.domain.exception.InvalidCredentialsException
import com.teamsparta.gogocard.domain.exception.ModelNotFoundException
import com.teamsparta.gogocard.domain.user.dto.request.CreateUserRequest
import com.teamsparta.gogocard.domain.user.dto.request.SignInRequest
import com.teamsparta.gogocard.domain.user.dto.response.SignInResponse
import com.teamsparta.gogocard.domain.user.dto.response.UserResponse
import com.teamsparta.gogocard.domain.user.model.UserEntity
import com.teamsparta.gogocard.domain.user.model.UserRole
import com.teamsparta.gogocard.domain.user.model.toResponse
import com.teamsparta.gogocard.domain.user.repository.UserRepository
import com.teamsparta.gogocard.infra.security.jwt.JwtPlugin
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class UserServiceImpl (
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin : JwtPlugin
) : UserService {

    @Transactional
    override fun signUp(request : CreateUserRequest): UserResponse {
        if (userRepository.existsByEmail(request.email)) {
            throw IllegalStateException ("email is already in use")
        }

        return userRepository.save(
            UserEntity (
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
        val user = userRepository.findByEmail(request.email) ?: throw ModelNotFoundException ("User", null)

        if (user.role.name != request.role || !passwordEncoder.matches(request.password, user.password) ){
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
}

