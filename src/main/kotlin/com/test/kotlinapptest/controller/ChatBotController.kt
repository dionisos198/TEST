package com.test.kotlinapptest.controller

import com.test.kotlinapptest.service.ChatBotService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/api/ai")
class ChatBotController(
        private val chatBotService: ChatBotService
) {

    @GetMapping("/ask")
    fun ask(
            @RequestParam q: String,
            @RequestParam userId: Long,
            @RequestParam(required = false) model: String?
    ): String = chatBotService.ask(q, userId, model)

}