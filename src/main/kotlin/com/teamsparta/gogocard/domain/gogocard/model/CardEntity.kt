package com.teamsparta.gogocard.domain.gogocard.model

import jakarta.persistence.*

@Entity
@Table(name = "cards")
class CardEntity (
    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "content", nullable = false)
    var content: String,

    @Column(name = "date", nullable = false)
    var date: String,

    @Column(name = "author", nullable = false)
    var author: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}