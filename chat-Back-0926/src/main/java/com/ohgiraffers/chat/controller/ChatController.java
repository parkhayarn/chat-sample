package com.ohgiraffers.chat.controller;

import com.ohgiraffers.chat.entity.ChatRoom;
import com.ohgiraffers.chat.repository.MessageRepository;
import com.ohgiraffers.chat.service.ChatService;
import com.ohgiraffers.chat.entity.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;
    private final MessageRepository messageRepository;

    public ChatController(ChatService chatService, MessageRepository messageRepository) {
        this.chatService = chatService;
        this.messageRepository = messageRepository;
    }

    @PostMapping("/rooms")
    public ChatRoom createChatRoom(@RequestBody String name) {
        return chatService.createChatRoom(name);
    }

    @GetMapping("/rooms")
    public List<ChatRoom> getAllChatRooms() {
        return chatService.getAllChatRooms();
    }

    @GetMapping("/rooms/{id}/messages")
    public List<com.ohgiraffers.chat.entity.Message> getMessages(@PathVariable Long id) {
        return chatService.getMessagesByChatRoom(id);
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/messages")  // 구독 경로 설정
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }
}