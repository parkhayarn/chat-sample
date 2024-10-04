package com.ohgiraffers.chat.controller;

import com.ohgiraffers.chat.model.ChatMessage;
import com.ohgiraffers.chat.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatController {

    private final SimpMessageSendingOperations messageSendingOperations;
    private final ChatMessageService chatMessageService;

    @Autowired
    public ChatController(SimpMessageSendingOperations messageSendingOperations, ChatMessageService chatMessageService) {
        this.messageSendingOperations = messageSendingOperations;
        this.chatMessageService = chatMessageService;
    }

    @GetMapping("/chatroom/{chatRoomId}/messages")
    public ResponseEntity<List<ChatMessage>> getMessages(@PathVariable Long chatRoomId) {
        List<ChatMessage> messages = chatMessageService.getMessagesByChatRoomId(chatRoomId);
        return ResponseEntity.ok(messages);
    }

    @MessageMapping("/message")
    public void receiveMessage(@Payload ChatMessage chatMessage) {
        if (chatMessage.getChatRoomId() == null) {
            throw new IllegalArgumentException("채팅방 ID가 누락되었습니다.");
        }

        String chatRoomId = chatMessage.getChatRoomId().toString();
        messageSendingOperations.convertAndSend("/sub/chatroom/" + chatRoomId, chatMessage);

        try {
            ChatMessage savedMessage = chatMessageService.saveMessage(chatMessage);
            System.out.println("저장된 메시지: " + savedMessage);
        } catch (Exception e) {
            System.err.println("메시지 저장 오류: " + e.getMessage());
            e.printStackTrace();
            // 여기서 적절한 예외 처리를 추가할 수 있습니다.
        }
    }
}