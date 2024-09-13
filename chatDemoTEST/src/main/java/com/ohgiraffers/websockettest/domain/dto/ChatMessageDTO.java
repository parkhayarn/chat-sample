package com.ohgiraffers.websockettest.domain.dto;

import lombok.Getter;
import lombok.Setter;

/*
* Enum인 MessageType은 서버가 메세지를 처리할 때 입장, 채팅, 퇴장을 구별하는데 사용됩니다.
* */
@Getter
@Setter
public class ChatMessageDTO {
    // 메시지 타입 : 입장, 채팅, 나감
    public enum MessageType {
        ENTER, TALK,QUIT
    }
    private MessageType type; // 메시지 타입
    private String roomId; // 방번호
    private String sender; // 메시지 보낸사람
    private String message; // 메시지
}