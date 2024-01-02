package com.teamsparta.gogocard.domain.gogocard.service

import com.teamsparta.gogocard.domain.gogocard.dto.CardResponse
import com.teamsparta.gogocard.domain.gogocard.dto.CreateCardRequest
import com.teamsparta.gogocard.domain.gogocard.dto.UpdateCardRequest

interface CardService {

    fun getCardList() : List<CardResponse>

    fun getCardById(cardId: Long) : CardResponse

    fun createCard(request: CreateCardRequest) : CardResponse

    fun updateCard(cardId:Long, request: UpdateCardRequest) : CardResponse

    fun deleteCard(cardId:Long)
}
