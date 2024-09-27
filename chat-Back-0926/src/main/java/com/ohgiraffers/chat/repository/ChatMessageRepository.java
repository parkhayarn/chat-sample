package com.ohgiraffers.chat.repository;

import com.ohgiraffers.chat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
