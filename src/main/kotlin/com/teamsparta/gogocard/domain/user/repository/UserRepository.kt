package com.teamsparta.gogocard.domain.user.repository

import com.teamsparta.gogocard.domain.user.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository <UserEntity, Long > {

    fun existsByEmail(email:String) : Boolean
    fun findByEmail(email: String) : UserEntity?

    fun findByUserName(userName : String) : UserEntity
}