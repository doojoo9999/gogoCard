package com.teamsparta.gogocard.domain.user.repository

import com.teamsparta.gogocard.domain.user.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface userRepository : JpaRepository <UserEntity, Long > {

}