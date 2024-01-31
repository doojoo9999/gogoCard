package com.teamsparta.gogocard.domain.gogocard.model

import com.teamsparta.gogocard.domain.gogocard.dto.response.CardResponse
import com.teamsparta.gogocard.domain.user.model.UserEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "cards")
class CardEntity (
    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "content", nullable = false)
    var content: String,

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne
    var user: UserEntity,

    @OneToMany(mappedBy = "card")
    var comments: MutableList<CommentEntity> = mutableListOf()

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "completed", nullable = false)
    private var _isCompleted: Boolean = false

    val isCompleted: Boolean
        get() = if(_isCompleted) false else true

    fun complete() {
        if(_isCompleted == true) {
            throw IllegalStateException ("complete already")
        }
        _isCompleted = true
    }

}

fun CardEntity.toResponse() : CardResponse {
    return CardResponse(
        id = id,
        title = title,
        content = content,
        date = LocalDateTime.now(),
        userId = user.id,
        complete = isCompleted
    )
}

fun CardEntity.toResponseWithComments() : CardResponse {
    return CardResponse(
        id = id,
        title = title,
        content = content,
        date = LocalDateTime.now(),
        userId = user.id,
        complete = isCompleted,
        comments = comments.map { it.toResponse() }
    )
}