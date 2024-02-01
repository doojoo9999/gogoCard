package com.teamsparta.gogocard.domain.user.model

import com.teamsparta.gogocard.domain.user.dto.response.SendMailResponse
import jakarta.persistence.*

@Entity
@Table(name = "mails")
class MailEntity (
    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "authcode", nullable = false)
    val authcode : String
) {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    val id : Long = 0L
}


