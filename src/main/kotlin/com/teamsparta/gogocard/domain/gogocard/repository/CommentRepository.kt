package com.teamsparta.gogocard.domain.gogocard.repository

import com.teamsparta.gogocard.domain.gogocard.model.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<CommentEntity, Long>{

    fun findByAuthor(author: String) : List<CommentEntity>

}