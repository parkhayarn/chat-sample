import { useEffect, useRef, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { callCreateChatRoomAPI } from "../../apis/ChatRoomCreateAPICall";

const CreateChatRoomForm = ({ roomId }) => {
    const [messages, setMessages] = useState([]);
    const [newMessage, setNewMessage] = useState('');

    useEffect(() => {
        // 채팅방 메시지 불러오기
        fetchChatMessages(roomId).then(setMessages);
    }, [roomId]);

    const handleSendMessage = () => {
        if (newMessage.trim()) {
            sendMessage(roomId, newMessage).then(() => {
                setMessages([...messages, { content: newMessage, sender: 'You' }]);
                setNewMessage('');
            });
        }
    };

    const fetchChatMessages = async (roomId) => {
        const response = await callCreateChatRoomAPI(roomId);
        return response.data;
    };

    const sendMessage = async (roomId, content) => {
        await callCreateChatRoomAPI(roomId, { content });
    };

    return (
        <div className="chat-room">
            <h2>Chat Room {roomId}</h2>
            <div className="messages">
                {messages.map((msg, index) => (
                    <div key={index}>
                        <strong>{msg.sender}:</strong> {msg.content}
                    </div>
                ))}
            </div>
            <input
                type="text"
                value={newMessage}
                onChange={(e) => setNewMessage(e.target.value)}
                placeholder="Type your message"
            />
            <button onClick={handleSendMessage}>Send</button>
        </div>
    );
};

export default CreateChatRoomForm;