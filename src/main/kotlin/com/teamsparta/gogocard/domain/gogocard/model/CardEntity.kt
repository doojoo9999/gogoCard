package com.teamsparta.gogocard.domain.gogocard.model

import com.teamsparta.gogocard.domain.gogocard.dto.CardResponse
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "cards")
class CardEntity (
    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "content", nullable = false)
    var content: String,

    @Column(name = "date", nullable = false)
    var date: LocalDateTime,

    @Column(name = "author", nullable = false)
    var author: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun CardEntity.toResponse() : CardResponse {
    return CardResponse(
        id = id!!,
        title = title,
        content = content,
        date = LocalDateTime.now(),
        author = author

    )
}