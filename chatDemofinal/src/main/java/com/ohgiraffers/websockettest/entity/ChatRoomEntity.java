package com.ohgiraffers.websockettest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/*
 * 특별할 건 없지만 방 한개마다 여러사용자들을 Set형태로 가지고 있습니다.
 * */

@Entity
@Table(name = "chat_room")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ChatRoomEntity {

    @OneToMany
    @JoinColumn(name = "room_id")
    private List<ChatMessageEntity> chattingMessages = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;

    public ChatMessageEntity createNewMessage(String senderUsername, String message) {
        ChatMessageEntity newMessage = ChatMessageEntity.builder()
                .sender(senderUsername)
                .message(message)
                .build();
        this.chattingMessages.add(newMessage);
        return newMessage;
    }
}
