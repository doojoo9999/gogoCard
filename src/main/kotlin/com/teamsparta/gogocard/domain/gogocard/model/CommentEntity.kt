package com.teamsparta.gogocard.domain.gogocard.model

import com.teamsparta.gogocard.domain.gogocard.dto.CardResponse
import com.teamsparta.gogocard.domain.gogocard.dto.CommentResponse
import com.teamsparta.gogocard.domain.user.model.UserEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "comments")
class CommentEntity (
    @Column (name = "content", nullable = false)
    var content: String,

    @Column (name = "password", nullable = false)
    var password: String,

    @ManyToOne
    @JoinColumn (name = "user_id", nullable = false)
    var user : UserEntity,

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    var card : CardEntity

){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
}

fun CommentEntity.toResponse() : CommentResponse {
    return CommentResponse(
        id = id,
        content = content,
        userId = user.id,
    )
}