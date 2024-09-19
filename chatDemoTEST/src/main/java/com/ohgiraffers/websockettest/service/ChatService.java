package com.ohgiraffers.websockettest.service;

import com.ohgiraffers.websockettest.entity.ChatRoomEntity;
import com.ohgiraffers.websockettest.repository.ChatRoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* 방을 만드는것과  방을 찾아주는 메소드가 있습니다.
방을 만들 때는 random한 방 이름으로 만듭니다.
DB와 연동된다면 방을 DB에 저장하겠지만, 아직은 DB연결이 없기때문에 Map형태로 방을 저장합니다.
* */
@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;

    public List<ChatRoomEntity> findAllRoom() {
        return this.chatRoomRepository.findAll();
    }

    public ChatRoomEntity findById(final long id) {
        return this.chatRoomRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    // createRoom 채팅방을 생성해서 생성된 방을 보여줄 수 있도록 하는곳
    // Id에 할당된 값을 알려줄려고
    @Transactional
    public ChatRoomEntity createRoom(final String name) {
        ChatRoomEntity newRoom = ChatRoomEntity.builder()
                .name(name)
                .build();
        return this.chatRoomRepository.save(newRoom);
    }
}
