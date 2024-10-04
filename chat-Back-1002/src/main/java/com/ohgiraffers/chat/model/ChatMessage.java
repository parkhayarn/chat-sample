package com.ohgiraffers.chat.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "chat_messages")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String message;
    private Long chatRoomId;
    private Long senderId;

    public ChatMessage() {
    }

    public ChatMessage(Long id, String name, String message, Long chatRoomId, Long senderId) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.chatRoomId = chatRoomId;
        this.senderId = senderId;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", chatRoomId=" + chatRoomId +
                ", senderId=" + senderId +
                '}';
    }
}