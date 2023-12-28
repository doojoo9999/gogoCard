package com.teamsparta.gogocard.domain.gogocard.service

import com.teamsparta.gogocard.domain.gogocard.dto.CardResponse
import com.teamsparta.gogocard.domain.gogocard.dto.CreateCardRequest
import com.teamsparta.gogocard.domain.gogocard.dto.UpdateCardRequest
import com.teamsparta.gogocard.domain.gogocard.model.CardEntity
import com.teamsparta.gogocard.domain.gogocard.repository.CardRepository
import org.springframework.stereotype.Service

@Service
class CardServiceImpl(
    private val cardRepository: CardRepository
) : CardService {

    override fun getCardList(): List<CardResponse> {
        TODO("모든 카드 리스트를 불러온다.")
    }

    override fun getCardById(cardId:Long): CardResponse {
        TODO("사람 이름을 바탕으로 카드를 불러온다.")
    }

    override fun createCard(request: CreateCardRequest): CardResponse {
        TODO("요청에 따라 할 일 카드를 생성한다.")
    }

    override fun updateCard(cardId:Long, request: UpdateCardRequest): CardResponse {
        TODO("db에 저장된 할 일 카드를 불러온 뒤, 원하는 값으로 수정하여 db에 CardResponse 형태로 저장한다.")
    }

    override fun deleteCard(cardId:Long): CardResponse {
        TODO("요청된 cardId와 일치하는 카드를 db에서 확인한 뒤, 해당 card를 db에서 삭제한다.")
    }
}