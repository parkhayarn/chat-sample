package com.ohgiraffers.chat.repository;

import com.ohgiraffers.chat.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatRoomId(Long chatRoomId);
}