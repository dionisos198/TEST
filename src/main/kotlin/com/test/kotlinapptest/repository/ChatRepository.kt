package com.test.kotlinapptest.repository

import com.test.kotlinapptest.entity.Chat
import com.test.kotlinapptest.entity.ChatThread
import com.test.kotlinapptest.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface ChatRepository: JpaRepository<Chat, Long> {

    fun findTopByChatThread_UserOrderByCreatedAtDesc(user: User): Chat?

    fun findByChatThreadOrderByCreatedAtAsc(chatThread: ChatThread): List<Chat>
}