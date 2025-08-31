package com.test.kotlinapptest.service

import com.test.kotlinapptest.entity.Chat
import com.test.kotlinapptest.entity.ChatThread
import com.test.kotlinapptest.entity.User
import com.test.kotlinapptest.repository.ChatRepository
import com.test.kotlinapptest.repository.ChatThreadRepository
import com.test.kotlinapptest.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.ai.chat.client.ChatClient
import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class ChatBotService(builder: ChatClient.Builder, private val userRepository: UserRepository,
                     private val chatThreadRepository: ChatThreadRepository, private val chatRepository: ChatRepository) {

    private val chatClient: ChatClient = builder.build()

    @Transactional
    fun ask(userMessage: String, userId: Long,model: String?): String {

        val user: User = userRepository.findById(userId).orElseThrow()
        val now = OffsetDateTime.now()

        // 가장 최근 채팅 조회
        val latestChat = chatRepository.findTopByChatThread_UserOrderByCreatedAtDesc(user)

        val chatThread = if (latestChat == null || latestChat.createdAt.plusMinutes(30).isBefore(now)) {
            chatThreadRepository.save(ChatThread(user = user, createdAt = now))
        } else {
            latestChat.chatThread
        }

        val pastChats = chatRepository.findByChatThreadOrderByCreatedAtAsc(chatThread)

        val historyMessages = pastChats.flatMap { chat ->
            listOf(
                    org.springframework.ai.chat.messages.UserMessage(chat.question),
                    org.springframework.ai.chat.messages.AssistantMessage(chat.answer)
            )
        }

        // AI 응답 생성
        val prompt = chatClient.prompt()
                .messages(historyMessages)
                .user(userMessage)

        if (model != null) {
            prompt.options(
                    org.springframework.ai.openai.OpenAiChatOptions.builder()
                            .model(model)
                            .build()
            )
        }

        val answer = prompt.call().content() ?: ""

        // 대화 저장
        val chat = Chat(
                chatThread = chatThread,
                question = userMessage,
                answer = answer,
                createdAt = now
        )
        chatRepository.save(chat)

        return answer
    }
}