package com.test.kotlinapptest.repository

import com.test.kotlinapptest.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {

    fun findByName(name: String): List<User>
}