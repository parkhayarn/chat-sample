import React, { useState, useEffect } from 'react';
import WebSocketService from './WebSocketService';
import axios from 'axios';

const ChatRoom = ({ room }) => {
const [messages, setMessages] = useState([]);
const [newMessage, setNewMessage] = useState('');

useEffect(() => {
    const fetchMessages = async () => {
    const response = await axios.get(`http://localhost:8080/messages/${room.id}`);
    setMessages(response.data);
    };
    fetchMessages();

    WebSocketService.connect(
    () => WebSocketService.subscribeToRoom(room.id, (message) => {
        setMessages((prevMessages) => [...prevMessages, message]);
    }),
    (error) => console.error('Connection error', error)
    );

    return () => {
    WebSocketService.disconnect();
    };
}, [room.id]);

const sendMessage = () => {
    if (newMessage.trim()) {
    const message = {
        content: newMessage,
        chatRoom: room
    };
    WebSocketService.sendMessage(room.id, message);
    setNewMessage('');
    }
};

return (
    <div>
    <h2>{room.roomName}</h2>
    <div className="messages">
        {messages.map((msg, index) => (
        <div key={index} className="message">
            {msg.content}
        </div>
        ))}
    </div>
    <div className="chat-input">
        <input
        type="text"
        value={newMessage}
        onChange={(e) => setNewMessage(e.target.value)}
        placeholder="Enter message"
        />
        <button onClick={sendMessage}>Send</button>
    </div>
    </div>
);
};

export default ChatRoom;
