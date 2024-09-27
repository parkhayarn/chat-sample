package com.ohgiraffers.chat.service;

import com.ohgiraffers.chat.dto.ChatMessageDTO;
import com.ohgiraffers.chat.entity.ChatMessage;
import com.ohgiraffers.chat.entity.ChatRoom;
import com.ohgiraffers.chat.repository.ChatMessageRepository;
import com.ohgiraffers.chat.repository.ChatRoomRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;

    private final ChatRoomRepository chatRoomRepository;

    public ChatService(ChatMessageRepository chatMessageRepository, ChatRoomRepository chatRoomRepository) {
        this.chatMessageRepository = chatMessageRepository;
        this.chatRoomRepository = chatRoomRepository;
    }

    public ChatMessageDTO saveAndSendMessage(ChatMessageDTO chatMessageDTO) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatMessageDTO.getChatRoomId())
                .orElseThrow(() -> new RuntimeException("Chat room not found"));

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(chatMessageDTO.getContent());
        chatMessage.setSender(chatMessageDTO.getSender());
        chatMessage.setChatRoom(chatRoom);

        chatMessage = chatMessageRepository.save(chatMessage);

        chatMessageDTO.setId(chatMessage.getId());
        return chatMessageDTO;
    }
}