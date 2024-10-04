package com.ohgiraffers.chat.model;

import lombok.*;

@Getter
@Setter
public class ChatMessageDTO {
    private Long id;
    private String name;
    private String message;
    private Long chatRoomId;
    private Long senderId;

    @Override
    public String toString() {
        return "ChatMessageDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", chatRoomId=" + chatRoomId +
                ", senderId=" + senderId +
                '}';
    }
}