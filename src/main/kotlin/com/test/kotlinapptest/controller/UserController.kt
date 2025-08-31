package com.test.kotlinapptest.controller

import com.test.kotlinapptest.dto.UserSaveDto
import com.test.kotlinapptest.entity.User
import com.test.kotlinapptest.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(
        private val userService: UserService
) {

    @PostMapping
    fun saveUser(@RequestBody userSaveDto: UserSaveDto): User{

        return userService.saveUser(userSaveDto);
    }

}