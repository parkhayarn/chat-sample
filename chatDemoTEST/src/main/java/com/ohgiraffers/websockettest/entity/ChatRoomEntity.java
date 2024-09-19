package com.ohgiraffers.websockettest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime creationTime;

    @OneToMany
    @JoinColumn(name = "room_id")
    private List<ChatMessageEntity> chattingMessages = new ArrayList<>();

}
