package com.ohgiraffers.websockettest.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/*
위에서 만든 handler를 이용하여 Websocket을 활성화하기 위한 Config 파일을 작성합니다.
@EnableWebSocket을 선언하여 Websocket을 활성화합니다. Websocket에 접속하기 위한 endpoint는 /ws/chat으로
설정하고 도메인이 다른 서버에서도 접속 가능하도록 CORS : setAllowedOrigins(“*”)를 설정을 추가해 줍니다.
이제 클라이언트가 ws://localhost:8080/ws/chat으로 커넥션을 연결하고 메시지 통신을 할 수 있는 기본적인 준비가
끝났습니다.
*/
@RequiredArgsConstructor
@Configuration
@EnableWebSocket   //이게 websocket 서버로서 동작하겠다는 어노테이션
public class WebSockConfig implements WebSocketConfigurer {
    private final WebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/ws/chat").setAllowedOrigins("*");
        // handler 등록,   js에서 new Websocket할 때 경로 지정
        //다른 url에서도 접속할 수있게(CORS방지)
    }
}
