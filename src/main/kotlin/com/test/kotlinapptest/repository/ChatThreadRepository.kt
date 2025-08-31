package com.test.kotlinapptest.repository

import com.test.kotlinapptest.entity.ChatThread
import org.springframework.data.jpa.repository.JpaRepository


interface ChatThreadRepository: JpaRepository<ChatThread,Long> {

}