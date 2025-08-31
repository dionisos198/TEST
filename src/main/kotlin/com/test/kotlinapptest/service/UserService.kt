package com.test.kotlinapptest.service

import com.test.kotlinapptest.dto.UserSaveDto
import com.test.kotlinapptest.entity.User
import com.test.kotlinapptest.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.OffsetDateTime

@Service
@Transactional
class UserService(
        private val userRepository: UserRepository
) {

    @Transactional
    fun saveUser(userSaveDto: UserSaveDto): User{

        val user = User(email = userSaveDto.email, password = userSaveDto.password, name= userSaveDto.name, createdAt = OffsetDateTime.now(), role = "member")

        return userRepository.save(user)
    }

}