package com.teamsparta.gogocard.domain.user.model

import com.teamsparta.gogocard.domain.user.dto.response.UserResponse
import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity (
    @Column(name = "user_name", nullable = false)
    var userName: String,

    @Column(name = "email", nullable = false)
    var email: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    var role: UserRole,

){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0
}

fun UserEntity.toResponse() : UserResponse {
    return UserResponse(
        id = id,
        email = email,
        userName = userName,
        role = role.name,
    )
}