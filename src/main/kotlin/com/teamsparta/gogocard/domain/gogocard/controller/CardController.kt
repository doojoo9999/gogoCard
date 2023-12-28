package com.teamsparta.gogocard.domain.gogocard.controller

import com.teamsparta.gogocard.domain.gogocard.dto.CardResponse
import com.teamsparta.gogocard.domain.gogocard.dto.CreateCardRequest
import com.teamsparta.gogocard.domain.gogocard.dto.UpdateCardRequest
import com.teamsparta.gogocard.domain.gogocard.service.CardService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/cards")
@RestController
class CardController (
    private val cardService: CardService
) {
    @GetMapping()
    fun getCardList() : ResponseEntity<List<CardResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardService.getCardList())

    }

    @GetMapping("/{cardId}")
    fun getCardById(@PathVariable cardId: Long) : ResponseEntity<CardResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardService.getCardById(cardId))
    }

    @PostMapping()
    fun createCard(@RequestBody createCardRequest: CreateCardRequest) : ResponseEntity<CardResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(cardService.createCard(createCardRequest))
    }

    @PutMapping("/{cardId}")
    fun updateCard(
        @PathVariable cardId : Long,
        @RequestBody updateCardRequest: UpdateCardRequest
    ): ResponseEntity<CardResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardService.updateCard(cardId, updateCardRequest))
    }

    @DeleteMapping("/{cardId}")
    fun deleteCard(@PathVariable cardId:String) : ResponseEntity<Unit>{
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }


}