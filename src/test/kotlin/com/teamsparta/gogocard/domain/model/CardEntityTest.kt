//package com.teamsparta.gogocard.domain.model
//
//import com.teamsparta.gogocard.domain.gogocard.model.CardEntity
//import com.teamsparta.gogocard.domain.gogocard.model.Complete
//import com.teamsparta.gogocard.domain.gogocard.repository.CardRepository
//import com.teamsparta.gogocard.domain.gogocard.service.CardServiceImpl
//import io.kotest.core.spec.style.BehaviorSpec
//import io.kotest.core.spec.style.DescribeSpec
//import io.kotest.extensions.spring.SpringExtension
//import io.mockk.clearAllMocks
//import io.mockk.every
//import io.mockk.junit5.MockKExtension
//import io.mockk.mockk
//import org.junit.jupiter.api.extension.ExtendWith
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.data.repository.findByIdOrNull
//
//@SpringBootTest
//@ExtendWith(MockKExtension::class)
//class CardEntityTest : BehaviorSpec(({
//    extension(SpringExtension){
//
//        afterContainer {
//            clearAllMocks()
//        }
//
//        val cardRepository = mockk<CardRepository>()
//        val cardService = CardServiceImpl(cardRepository)
//
//        Given("이미 Card가 Complete 처리가 되었을 때") {
//            When("다시 Complete를 요청하면"){
//                Then("NO_COMPLETE로 변경되어야 한다."){
//
//                    val cardId = 1L
//
//                    every { cardRepository.findByIdOrNull(any()) } returns null
//
//                    shouldBe <
//
//                }
//            }
//        }
//
//
//    }
//
//
//
//})