package com.test.kotlinapptest.entity

import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity
class Chat(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "chat_thread_id", nullable = false)
        val chatThread: ChatThread,

        @Column(nullable = false)
        val question: String,

        @Column(nullable = false)
        val answer: String,

        @Column(nullable = false, columnDefinition = "timestamptz")
        val createdAt: OffsetDateTime = OffsetDateTime.now()
)