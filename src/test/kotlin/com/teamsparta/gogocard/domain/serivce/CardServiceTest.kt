package com.teamsparta.gogocard.domain.serivce

import com.teamsparta.gogocard.domain.exception.ModelNotFoundException
import com.teamsparta.gogocard.domain.gogocard.repository.CardRepository
import com.teamsparta.gogocard.domain.gogocard.repository.CommentRepository
import com.teamsparta.gogocard.domain.gogocard.service.CardServiceImpl
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
@ExtendWith(MockKExtension::class)
class CardServiceTest : BehaviorSpec ({
    extension(SpringExtension)

    afterContainer {
        clearAllMocks()
    }

    val cardRepository = mockk<CardRepository>()
    val commentRepository = mockk<CommentRepository>()

    val cardService = CardServiceImpl(cardRepository, commentRepository)

    Given("Card 목록이 존재하지 않을 때") {
        When("특정 Card를 요청하면"){
            Then("ModelNotFoundException이 발생해야 한다.") {
                val cardId = 1L
                every { cardRepository.findByIdOrNull(any()) } returns null

                shouldThrow<ModelNotFoundException> {
                    cardService.getCardById(cardId)
                }
            }
        }
    }

})