package com.teamsparta.gogocard.domain.gogocard.repository

import com.teamsparta.gogocard.domain.gogocard.model.CardEntity
import com.teamsparta.gogocard.domain.gogocard.model.Complete
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CustomCardRepository {

    fun searchCardListByTitle(title: String) : List<CardEntity>

    fun searchCardListByComplete(isCompleted: Boolean) : List<CardEntity>

    fun findByPageableAndComplete (pageable: Pageable, cardComplete: Complete?): Page<CardEntity>

}