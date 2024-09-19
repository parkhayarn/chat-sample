package com.ohgiraffers.websockettest.config;

import com.ohgiraffers.websockettest.handler.ChatWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // WebSocket 핸들러를 등록하는 메서드
        // registry: WebSocket 핸들러를 등록하는 데 사용되는 WebSocketHandlerRegistry 객체

        // addHandler: 새로운 WebSocket 핸들러를 추가합니다.
        // ChatWebSocketHandler: WebSocket 핸들러 클래스의 인스턴스
        // "/ChattingServer": WebSocket 서버의 엔드포인트 URL

        // setAllowedOrigins("*"): 모든 출처(origin)에서의 접근을 허용합니다.
        registry.addHandler(new ChatWebSocketHandler(), "/ChattingServer").setAllowedOrigins("*");
    }
}
