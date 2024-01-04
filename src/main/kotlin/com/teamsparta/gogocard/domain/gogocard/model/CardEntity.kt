package com.teamsparta.gogocard.domain.gogocard.model

import com.teamsparta.gogocard.domain.gogocard.dto.CardResponse
import jakarta.persistence.*
import org.springframework.data.jpa.domain.AbstractPersistable_.id
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

    @Column(name = "complete")
    var complete: Boolean,

    @OneToMany(mappedBy = "card")
    var comments: MutableList<CommentEntity> = mutableListOf()

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
}

fun CardEntity.toResponse() : CardResponse {
    return CardResponse(
        id = id,
        title = title,
        content = content,
        date = LocalDateTime.now(),
        author = author,
        complete = complete
    )
}

fun CardEntity.toResponseWithComments() : CardResponse {
    return CardResponse(
        id = id,
        title = title,
        content = content,
        date = LocalDateTime.now(),
        author = author,
        complete = complete,
        comments = comments.map { it.toResponse() }
    )
}