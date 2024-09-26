import React, { useState, useEffect } from 'react';
import { fetchChatMessages, sendMessage } from '../apis/chatApi';

const ChatRoom = ({ roomId }) => {
    const [messages, setMessages] = useState([]);
    const [message, setMessage] = useState('');

    useEffect(() => {
        fetchChatMessages(roomId).then(setMessages);
    }, [roomId]);

    const handleSendMessage = () => {
        sendMessage(roomId, message).then(() => {
            setMessages([...messages, { content: message, sender: 'Me' }]);
            setMessage('');
        });
    };

    return (
        <div className="chat-room">
            <div className="messages">
                {messages.map((msg, index) => (
                    <div key={index}>{msg.sender}: {msg.content}</div>
                ))}
            </div>
            <input value={message} onChange={(e) => setMessage(e.target.value)} />
            <button onClick={handleSendMessage}>Send</button>
        </div>
    );
};

export default ChatRoom;
