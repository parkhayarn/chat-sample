import React, { useState, useEffect } from 'react';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import { sendMessage, getMessages } from '../services/chatService';
import axios from 'axios';

const ChatRoom = ({ roomId }) => {
    console.log("ChatRoom component rendered", roomId);

    const [stompClient, setStompClient] = useState(null);
    const [messages, setMessages] = useState([]);
    const [messageInput, setMessageInput] = useState('');
    const [rooms, setRooms] = useState([]);

    // getChatRooms 함수 정의
    const getChatRooms = async () => {
        try {
            const response = await axios.get('/api/chatrooms'); // API 호출
            setRooms(response.data); // 상태 업데이트
        } catch (error) {
            console.error('Error fetching chat rooms:', error);
        }
    };

    useEffect(() => {
        getChatRooms(); // 컴포넌트가 마운트될 때 호출
    }, []);

    useEffect(() => {
        loadRooms();
    }, []);

    const loadRooms = () => {
        getChatRooms().then((data) => {
            console.log("Loaded chat rooms:", data);
            setRooms(data);
        });
    };

    useEffect(() => {
        const socket = new SockJS('http://localhost:8080/ws');
        const client = Stomp.over(socket);

        client.connect({}, () => {
            setStompClient(client);
            client.subscribe(`/topic/chat/${roomId}`, (message) => {
                const receivedMessage = JSON.parse(message.body);
                setMessages((prevMessages) => [...prevMessages, receivedMessage]);
            });
        });

        getMessages(roomId).then((data) => setMessages(data));

        return () => {
            if (client) {
                client.disconnect();
            }
        };
    }, [roomId]);

    const handleSendMessage = () => {
        if (messageInput && stompClient) {
            const message = { content: messageInput, sender: 'User', chatRoomId: roomId };
            stompClient.send('/app/chat.sendMessage', {}, JSON.stringify(message));
            sendMessage(message);
            setMessageInput('');
        }
    };

    return (
        <div>
            <div>
                {messages.map((msg, index) => (
                    <div key={index}>{`${msg.sender}: ${msg.content}`}</div>
                ))}
                {rooms.map(room => (
                    <div key={room.id}>{room.name}</div> // 채팅방 목록 렌더링
                ))}
            </div>
            <input
                type="text"
                value={messageInput}
                onChange={(e) => setMessageInput(e.target.value)}
            />
            <button onClick={handleSendMessage}>Send</button>
        </div>
    );
};

export default ChatRoom;