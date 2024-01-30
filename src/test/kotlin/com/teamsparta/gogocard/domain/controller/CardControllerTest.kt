package com.teamsparta.gogocard.domain.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.teamsparta.gogocard.domain.gogocard.dto.response.CardResponse
import com.teamsparta.gogocard.domain.gogocard.service.CardService
import com.teamsparta.gogocard.infra.security.jwt.JwtPlugin
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockKExtension::class)
class CardControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val jwtPlugin: JwtPlugin,
    private val jacksonObjectMapper: ObjectMapper
) : DescribeSpec({
    extension(SpringExtension)

    afterContainer {
        clearAllMocks()
    }

    val cardService = mockk<CardService>()

    describe("GET /api/cards/{cardid}"){
        context("존재하는 ID를 요청할 때"){
            it("200 status code를 응답한다.") {
                val cardId = 1L

                every { cardService.getCardById(any()) } returns CardResponse(
                    id = cardId,
                    title = "test_title",
                    content = "test_content",
                    userId = 1,
                    date = null,
                    complete = false,
                    comments = mutableListOf()
                )

                val jwtToken = jwtPlugin.generateAccessToken(
                    subject = "1",
                    email = "test@gmail.com",
                    role = "ADMIN"
                )
                val result = mockMvc.perform(
                    get("/api/cards/$cardId")
                        .header("Authorization", "Bearer $jwtToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andReturn()

                result.response.status shouldBe 200

                val responseDto = jacksonObjectMapper().readValue(
                        result.response.contentAsString,
                        CardResponse::class.java
                )

                responseDto.id shouldBe cardId
            }
        }
    }


}) {
}