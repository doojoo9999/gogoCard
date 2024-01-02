package com.teamsparta.gogocard.domain.gogocard.repository

import com.teamsparta.gogocard.domain.gogocard.model.CardEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CardRepository : JpaRepository <CardEntity, Long> {
}