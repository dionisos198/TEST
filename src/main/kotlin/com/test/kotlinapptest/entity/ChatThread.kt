package com.test.kotlinapptest.entity

import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity
class ChatThread(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        val user: User,

        @Column(nullable = false, columnDefinition = "timestamptz")
        val createdAt: OffsetDateTime = OffsetDateTime.now(),

)