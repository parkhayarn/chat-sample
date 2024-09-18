package com.ohgiraffers.websockettest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
 * Enum인 MessageType은 서버가 메세지를 처리할 때 입장, 채팅, 퇴장을 구별하는데 사용됩니다.
 * */
@Entity
@Table(name = "chat_message")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ChatMessageEntity {

    @OneToMany
    @JoinColumn(name = "room_id")
    private List<ChatRoomEntity> chatRoomEntityList = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    // 뭘써야할까?...
}