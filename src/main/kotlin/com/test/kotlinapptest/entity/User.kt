package com.test.kotlinapptest.entity

import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity
@Table(name = "users")
class User(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(nullable = false)
        var email: String,

        @Column(nullable = false)
        var password: String,

        @Column(nullable = false)
        var name: String,

        @Column(nullable = false, columnDefinition = "timestamptz")
        var createdAt: OffsetDateTime,

        @Column(nullable = false)
        var role: String // "member" or "admin"
)