package com.ohgiraffers.chat.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDTO {
    private Long id;
    private String sender;
    private String content;
    private Long chatRoomId;
    private String timestamp;
}