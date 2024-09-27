import React, { useState, useEffect } from 'react';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import { sendMessage, getMessages } from '../services/chatService';

const ChatRoom = ({ roomId }) => {
    const [stompClient, setStompClient] = useState(null);
    const [messages, setMessages] = useState([]);
    const [messageInput, setMessageInput] = useState('');

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