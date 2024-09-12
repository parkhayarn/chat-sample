package com.ohgiraffers.chat3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class ChatWebSocketHandler extends TextWebSocketHandler {

    // 접속한 클라이언트의 WebSocket 세션을 관리할 Set
    private static Set<WebSocketSession> clients = Collections.synchronizedSet(new HashSet<>());
    private static Set<ChatRoom> chatRooms = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 클라이언트가 WebSocket 연결을 성공적으로 수립했을 때 호출됨
        clients.add(session); // 세션을 클라이언트 Set에 추가
        System.out.println("웹소켓 연결: " + session.getId());

        // 채팅방을 만든사람, 채팅이름, 생성일시
        String creator = session.getId(); //
        String chatName = "채팅방이름";
        LocalDateTime.now();
        ChatRoom chatRoom = new ChatRoom();

        chatRooms.add(chatRoom); // 채팅방 정보 Set에 추가
        // 로그 출력
        System.out.println("만든사람: " + chatRoom.getCreator() + ", 채팅방 생성: " + chatRoom.getChatName() + ", 생성일시: " + chatRoom.getCreationTime());
    }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 클라이언트로부터 텍스트 메시지를 수신했을 때 호출됨
        System.out.println("메시지 전송: " + session.getId() + " : " + message.getPayload());

        // 챗룸아이디, 보내는 사람, 메세지내용, 생성일시
        String chatRoomId = "chatRoom1"; // 예시: 실제 챗룸 아이디로 변경
        String senderId = session.getId(); // 보낸 사람의 세션 ID
        String messageContent = message.getPayload(); // 메시지 내용
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); // 생성일시

        // 로그 출력
        System.out.println("챗룸 아이디: " + chatRoomId);
        System.out.println("보내는 사람: " + senderId);
        System.out.println("메시지 내용: " + messageContent);
        System.out.println("생성 일시: " + timestamp);


        synchronized (clients) {
            // 모든 클라이언트에게 메시지 전송
            for (WebSocketSession client : clients) {
                if (!client.equals(session)) { // 메시지를 보낸 클라이언트 자신을 제외하고
                    client.sendMessage(new TextMessage(message.getPayload())); // 다른 클라이언트에게 메시지 전송
                }
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 클라이언트가 WebSocket 연결을 닫았을 때 호출됨
        clients.remove(session); // 세션을 클라이언트 Set에서 제거
        System.out.println("웹소켓 종료: " + session.getId());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // WebSocket 통신 중 에러가 발생했을 때 호출됨
        System.out.println("에러 발생: " + session.getId());
        exception.printStackTrace();
    }
}
