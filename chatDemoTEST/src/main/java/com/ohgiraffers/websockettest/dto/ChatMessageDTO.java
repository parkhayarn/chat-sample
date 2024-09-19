package com.ohgiraffers.websockettest.dto;

import com.ohgiraffers.websockettest.entity.ChatRoomEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChatMessageDTO {

    private ChatRoomEntity chatRoom;
    private String sender; // 메시지 보낸사람
    private String message; // 메시지

}
