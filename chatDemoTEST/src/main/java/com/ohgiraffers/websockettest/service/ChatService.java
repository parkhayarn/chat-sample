package com.ohgiraffers.websockettest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohgiraffers.websockettest.domain.dto.ChatRoomDTO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/*
* 방을 만드는것과  방을 찾아주는 메소드가 있습니다.
방을 만들 때는 random한 방 이름으로 만듭니다.
DB와 연동된다면 방을 DB에 저장하겠지만, 아직은 DB연결이 없기때문에 Map형태로 방을 저장합니다.
* */
@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ObjectMapper objectMapper;
    private Map<String, ChatRoomDTO> chatRooms;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoomDTO> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoomDTO findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    public ChatRoomDTO createRoom(String name) {
        String randomId = UUID.randomUUID().toString();
        ChatRoomDTO chatRoom = ChatRoomDTO.builder()
                .roomId(randomId)
                .name(name)
                .build();
        chatRooms.put(randomId, chatRoom);
        return chatRoom;
    }
}
