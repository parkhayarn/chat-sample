package com.ohgiraffers.chat.dto;

import lombok.Data;

@Data
public class ChatMessageDTO {
    private Long id;
    private String content;
    private String sender;
    private Long chatRoomId;
}