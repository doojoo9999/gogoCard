package com.teamsparta.gogocard.domain.gogocard.service

import com.teamsparta.gogocard.domain.exception.ModelNotFoundException
import com.teamsparta.gogocard.domain.gogocard.dto.CardResponse
import com.teamsparta.gogocard.domain.gogocard.dto.CreateCardRequest
import com.teamsparta.gogocard.domain.gogocard.dto.UpdateCardRequest
import com.teamsparta.gogocard.domain.gogocard.model.CardEntity
import com.teamsparta.gogocard.domain.gogocard.model.toResponse
import com.teamsparta.gogocard.domain.gogocard.repository.CardRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CardServiceImpl(
    private val cardRepository: CardRepository
) : CardService {

    override fun getCardList(): List<CardResponse> {
        return cardRepository.findAll().map { it.toResponse() }
    }

    override fun getCardById(cardId:Long): CardResponse {
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException ("Card", cardId)
        return card.toResponse()
    }
    @Transactional
    override fun createCard(request: CreateCardRequest): CardResponse {
        return cardRepository.save(
            CardEntity(
                title = request.title,
                content = request.content,
                author = request.author,
                date = request.date
            )
        ).toResponse()
//        TODO("요청에 따라 할 일 카드를 생성한다.")
    }

    @Transactional
    override fun updateCard(cardId:Long, request: UpdateCardRequest): CardResponse {
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException ("Card", cardId)
        val (title, content, author) = request

        card.title = title
        card.content = content
        card.author = author

        return cardRepository.save(card).toResponse()

//        TODO("db에 저장된 할 일 카드를 불러온 뒤, 원하는 값으로 수정하여 db에 CardResponse 형태로 저장한다.")
    }

    @Transactional
    override fun deleteCard(cardId:Long) {
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException ("Card", cardId)
        cardRepository.delete(card)


//        TODO("요청된 cardId와 일치하는 카드를 db에서 확인한 뒤, 해당 card를 db에서 삭제한다.")
    }
}