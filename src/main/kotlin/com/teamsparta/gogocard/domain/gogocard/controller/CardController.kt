package com.teamsparta.gogocard.domain.gogocard.controller

import com.teamsparta.gogocard.domain.gogocard.dto.*
import com.teamsparta.gogocard.domain.gogocard.model.toResponse
import com.teamsparta.gogocard.domain.gogocard.repository.CardRepository
import com.teamsparta.gogocard.domain.gogocard.service.CardService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/cards")
@RestController
class CardController(
    private val cardService: CardService,
    private val cardRepository: CardRepository
) {
    @GetMapping()
    fun getCardList(): ResponseEntity<List<CardResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardService.getCardList())

    }

    @GetMapping("/{cardId}")
    fun getCardById(@PathVariable cardId: Long): ResponseEntity<CardResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardService.getCardById(cardId))
    }

    @PostMapping()
    fun createCard(@RequestBody createCardRequest: CreateCardRequest): ResponseEntity<CardResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(cardService.createCard(createCardRequest))
    }

    @PutMapping("/{cardId}")
    fun updateCard(
        @PathVariable cardId: Long,
        @RequestBody updateCardRequest: UpdateCardRequest
    ): ResponseEntity<CardResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardService.updateCard(cardId, updateCardRequest))
    }

    @DeleteMapping("/{cardId}")
    fun deleteCard(@PathVariable cardId: Long): ResponseEntity<Unit> {
        cardService.deleteCard(cardId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

    @PostMapping("/{cardId}/comments")
    fun createComment(
        @PathVariable cardId: Long,
        @RequestBody createCommentRequest: CreateCommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(cardService.createComment(cardId, createCommentRequest))
    }

    @PutMapping("/{cardId}/comments/{commentId}")
    fun updateComment(
        @PathVariable cardId: Long, commentId: Long,
        @RequestBody updateCommentRequest: UpdateCommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardService.updateComment(cardId, commentId, updateCommentRequest))
    }


    @DeleteMapping("/{cardId}/comments/{commentId}")
    fun deleteComment(
        @PathVariable cardId: Long, commentId: Long
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

    @GetMapping("/sort")
    fun getCardListBySort(
        @RequestParam("sort") sort: String
    ): List<CardResponse> {
        val card = cardRepository.findAll()
        return when (sort) {
            "asc" -> card.sortedBy { it.date }
            "desc" -> card.sortedByDescending { it.date }
            else -> card
        }.map { it.toResponse() }
    }

    @GetMapping("/{cardId}/author")
    fun getCardByAuthor(
        @PathVariable cardId: Long,
        @RequestParam author: String,
        request: CallCardByAuthorRequest
    ): ResponseEntity<List<CardResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardService.getCardByAuthor(cardId, author, request))
    }
}