package com.ohgiraffers.websockettest.domain.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

/*
* 특별할 건 없지만 방 한개마다 여러사용자들을 Set형태로 가지고 있습니다.
* */
@Getter
public class ChatRoomDTO {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();
    @Builder
    public ChatRoomDTO(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }
}
