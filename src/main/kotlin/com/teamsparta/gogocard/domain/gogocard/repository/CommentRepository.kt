package com.teamsparta.gogocard.domain.gogocard.repository

import com.teamsparta.gogocard.domain.gogocard.model.CommentEntity
import com.teamsparta.gogocard.domain.user.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<CommentEntity, Long>{

    fun findByUserId(userId: Long) : List<CommentEntity>

}