import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';

const WebSocketService = (function() {
let client = null;
let subscriptions = {};

const connect = (onConnected, onError) => {
    const socket = new SockJS('http://localhost:8080/ws');
    client = new Client({
    webSocketFactory: () => socket,
    onConnect: () => {
        console.log('WebSocket connected');
        onConnected();
    },
    onStompError: (error) => {
        console.error('WebSocket error', error);
        onError(error);
    }
    });
    client.activate();
};

const subscribeToRoom = (roomId, onMessageReceived) => {
    if (client && client.connected) {
    subscriptions[roomId] = client.subscribe(`/topic/${roomId}`, (message) => {
        onMessageReceived(JSON.parse(message.body));
    });
    }
};

const sendMessage = (roomId, message) => {
    if (client && client.connected) {
    client.publish({
        destination: `/app/sendMessage/${roomId}`,
        body: JSON.stringify(message)
    });
    }
};

const disconnect = () => {
    if (client) {
    client.deactivate();
    }
};

return { connect, subscribeToRoom, sendMessage, disconnect };
})();

export default WebSocketService;
