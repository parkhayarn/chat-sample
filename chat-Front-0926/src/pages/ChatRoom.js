import React, { useEffect, useState } from 'react';
import { connectWebSocket, sendMessage } from '../modules/APICall';

const ChatRoom = ({ roomId }) => {
    const [messages, setMessages] = useState([]);
    const [newMessage, setNewMessage] = useState('');

    useEffect(() => {
        connectWebSocket((msg) => {
            const newMsg = JSON.parse(msg.body);
            setMessages((prevMessages) => [...prevMessages, newMsg]);
        });
    }, [roomId]);

    const handleSendMessage = () => {
        sendMessage({ content: newMessage, roomId });
        setNewMessage('');
    };

    return (
        <div>
            <ul>
                {messages.map((msg, index) => (
                    <li key={index}>{msg.sender}: {msg.content}</li>
                ))}
            </ul>
            <input
                type="text"
                value={newMessage}
                onChange={(e) => setNewMessage(e.target.value)}
                placeholder="메시지 입력"
            />
            <button onClick={handleSendMessage}>전송</button>
        </div>
    );
};

export default ChatRoom;
