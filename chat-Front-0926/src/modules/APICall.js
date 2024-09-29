import { Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

let stompClient = null;
let retries = 0;
const maxRetries = 5;  // 최대 재시도 횟수

// WebSocket 연결 함수
export const connectWebSocket = (onMessageReceived) => {
    const socket = new SockJS('http://localhost:8080/ws');  // WebSocket 서버 주소
    stompClient = Stomp.over(socket);

    const onConnectSuccess = (frame) => {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', onMessageReceived);  // 메시지 구독 경로
        retries = 0;  // 성공 시 재시도 횟수 초기화
    };

    const onConnectError = (error) => {
        console.error('WebSocket connection error:', error);
        if (retries < maxRetries) {
            retries += 1;
            console.log(`Retrying connection... (${retries}/${maxRetries})`);
            setTimeout(() => {
                connectWebSocket(onMessageReceived);  // 일정 시간 후 재시도
            }, 5000);  // 5초 후 재시도
        } else {
            console.error('Max retries reached. Could not connect to WebSocket.');
        }
    };

    // WebSocket 연결 시도
    stompClient.connect({}, onConnectSuccess, onConnectError);
};

// 메시지 전송 함수
export const sendMessage = (message) => {
    if (stompClient && stompClient.connected) {
        // 메시지를 /app/chat.sendMessage 경로로 전송
        stompClient.send('/app/chat.sendMessage', {}, JSON.stringify(message));
    } else {
        console.error('STOMP connection is not established.');
    }
};
