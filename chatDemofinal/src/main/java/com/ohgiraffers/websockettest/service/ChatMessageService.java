package com.ohgiraffers.websockettest.service;

import com.ohgiraffers.websockettest.entity.ChatMessageEntity;
import com.ohgiraffers.websockettest.entity.ChatRoomEntity;
import com.ohgiraffers.websockettest.repository.ChatMessageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatService chatService;

    public List<ChatMessageEntity> findAllMessage() {
        return this.chatMessageRepository.findAll();
    }

    // (계속 코딩하세용) RuntimeException 하나로도 1시간 설명 쌉가능
    // 알긴 해야돼 나중에 Checked Exception & Unchecked Exception 공부해오세용
    // + Java Exception Hierarchy
    public ChatMessageEntity findMessageById(final long id) {
        return this.chatMessageRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Transactional
    public ChatMessageEntity createMessage(final long roomId, final String senderUsername, final String message) {
        // 해당 id를 가지는 방을 찾아서
        ChatRoomEntity chatRoomEntity = chatService.findById(roomId);
        // 해당 방과 연결된 새 메시지를 추가
        ChatMessageEntity newMessage = chatRoomEntity.createNewMessage(senderUsername, message);
        return newMessage;
    }
}
