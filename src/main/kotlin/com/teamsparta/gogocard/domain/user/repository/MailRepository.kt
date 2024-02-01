package com.teamsparta.gogocard.domain.user.repository

import com.teamsparta.gogocard.domain.user.model.MailEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MailRepository : JpaRepository<MailEntity, Long> {

    fun findAllByEmail (email: String) : MailEntity?

    fun deleteByEmail (email: String) : MailEntity?

}