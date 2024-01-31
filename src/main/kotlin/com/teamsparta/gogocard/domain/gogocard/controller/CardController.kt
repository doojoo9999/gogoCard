package com.teamsparta.gogocard.domain.gogocard.controller

import com.teamsparta.gogocard.domain.gogocard.dto.request.*
import com.teamsparta.gogocard.domain.gogocard.dto.response.CardResponse
import com.teamsparta.gogocard.domain.gogocard.dto.response.CommentResponse
import com.teamsparta.gogocard.domain.gogocard.model.toResponse
import com.teamsparta.gogocard.domain.gogocard.repository.CardRepository
import com.teamsparta.gogocard.domain.gogocard.service.CardService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/cards")
@RestController
class CardController(
    private val cardService: CardService,
    private val cardRepository: CardRepository
) {

    @GetMapping("/search/title")
    @PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN')")
    fun searchCardListWithTitle(
        @RequestParam(value = "title") title:String
    ): ResponseEntity<List<CardResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardService.searchCardListWithTitle(title))
    }

    @GetMapping("/search/complete")
    @PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN')")
    fun searchCardListWithComplete(
        @RequestParam(value = "isCompleted") isCompleted:Boolean
    ): ResponseEntity<List<CardResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardService.searchCardListWIthComplete(isCompleted))
    }


    @GetMapping()
    @PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN')")
    fun getCardList(
        @PageableDefault(
            size = 15,
            sort = ["id"]
    ) pageable: Pageable,
        @RequestParam(value = "_isCompleted", required = false) _isCompleted: Boolean?

        ): ResponseEntity<Page<CardResponse>> {

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardService.getPaginatedCardList(pageable, _isCompleted))

    }

    @GetMapping("/{cardId}")
    @PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN')")
    fun getCardById(@PathVariable cardId: Long): ResponseEntity<CardResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardService.getCardById(cardId))
    }

    @PostMapping()
    @PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN')")
    fun createCard(@RequestBody createCardRequest: CreateCardRequest): ResponseEntity<CardResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(cardService.createCard(createCardRequest))
    }

    @PutMapping("/{cardId}")
    @PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN')")
    fun updateCard(
        @PathVariable cardId: Long,
        @RequestBody updateCardRequest: UpdateCardRequest
    ): ResponseEntity<CardResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardService.updateCard(cardId, updateCardRequest))
    }

    @DeleteMapping("/{cardId}")
    @PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN')")
    fun deleteCard(@PathVariable cardId: Long): ResponseEntity<Unit> {
        cardService.deleteCard(cardId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

    @PostMapping("/{cardId}/comments")
    @PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN')")
    fun createComment(
        @PathVariable cardId: Long,
        @RequestBody createCommentRequest: CreateCommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(cardService.createComment(cardId, createCommentRequest))
    }

    @PutMapping("/{cardId}/comments/{commentId}")
    @PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN')")
    fun updateComment(
        @PathVariable cardId: Long,
        @PathVariable commentId: Long,
        @RequestBody updateCommentRequest: UpdateCommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardService.updateComment(cardId, commentId, updateCommentRequest))
    }


    @DeleteMapping("/{cardId}/comments/{commentId}")
    @PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN')")
    fun deleteComment(
        @PathVariable cardId: Long,
        @PathVariable commentId: Long
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

//    @GetMapping("/sort")
//    @PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN')")
//    fun getCardListBySort(
//        @RequestParam("sort") sort: String
//    ): List<CardResponse> {
//        val card = cardRepository.findAll()
//        return when (sort) {
//            "asc" -> card.sortedBy { it.date }
//            "desc" -> card.sortedByDescending { it.date }
//            else -> card.sortedBy { it.date }
//        }.map { it.toResponse() }
//    }

    @GetMapping("/{cardId}/author")
    @PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN')")
    fun getCardByAuthor(
        @PathVariable cardId: Long,
        @RequestParam userId: Long,
        request: CallCardByUserIdRequest
    ): ResponseEntity<List<CardResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardService.getCardByUserId(cardId, userId, request))
    }

    @PatchMapping("/{cardId}/complete")
    @PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN')")
    fun completeCard(
        @PathVariable cardId: Long,
    ): ResponseEntity<CardResponse>{

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardService.completeCard(cardId))
    }


}