package com.teamsparta.gogocard.domain.gogocard.repository

import com.teamsparta.gogocard.domain.gogocard.model.CardEntity
import com.teamsparta.gogocard.domain.gogocard.model.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CardRepository : JpaRepository <CardEntity, Long> {

    fun findByAuthor(author: String) : List<CardEntity>

}