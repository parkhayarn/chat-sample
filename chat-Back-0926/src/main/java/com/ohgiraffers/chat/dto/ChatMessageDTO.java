package com.ohgiraffers.chat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDTO {
    private Long id;
    private String sender;
    private String content;
    private Long roomId;
    private String timestamp;
}