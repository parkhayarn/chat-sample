package com.ohgiraffers.websockettestreact.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohgiraffers.websockettestreact.dto.Message;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    // 접속한 클라이언트의 WebSocket 세션을 관리할 Set
    private static Set<WebSocketSession> clients = Collections.synchronizedSet(new HashSet<>());
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 클라이언트가 WebSocket 연결을 성공적으로 수립했을 때 호출됨
        clients.add(session); // 세션을 클라이언트 Set에 추가
        System.out.println("웹소켓 연결: " + session.getId());
    }



    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 클라이언트로부터 텍스트 메시지를 수신했을 때 호출됨
        System.out.println("메시지 전송: " + session.getId() + " : " + message.getPayload());
        String payload = message.getPayload();
        Message msg = objectMapper.readValue(payload, Message.class);

        synchronized (clients) {
            // 모든 클라이언트에게 메시지 전송
            for (WebSocketSession client : clients) {
                if (!client.equals(session)) { // 메시지를 보낸 클라이언트 자신을 제외하고
                    if ("join".equals(msg.getType())) {
                        client.sendMessage(new TextMessage(msg.getUserId() + "님이 입장하셨습니다."));
                    } else if ("leave".equals(msg.getType())) {
                        client.sendMessage(new TextMessage(msg.getUserId() + "님이 퇴장하셨습니다."));
                    } else if ("message".equals(msg.getType())) {
                        client.sendMessage(new TextMessage(msg.getUserId() + " | " + msg.getMessage()));
                    }
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
