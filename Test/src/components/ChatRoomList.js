import React, { useState, useEffect } from 'react';
import { getChatRooms, createChatRoom, leaveChatRoom } from '../services/chatRoomService';

const ChatRoomList = ({ onRoomSelect }) => {
    console.log("ChatRoomList component rendered");
    
    const [rooms, setRooms] = useState([]);
    const [newRoomName, setNewRoomName] = useState('');

    useEffect(() => {
        loadRooms();
    }, []);

    const loadRooms = () => {
        getChatRooms().then((data) => setRooms(data));
    };

    const handleCreateRoom = () => {
        if (newRoomName) {
            createChatRoom({ name: newRoomName }).then(() => {
                setNewRoomName('');
                loadRooms();
            });
        }
    };

    const handleLeaveRoom = (roomId) => {
        leaveChatRoom(roomId).then(() => {
            loadRooms();
        });
    };

    return (
        <div>
            <h2>Chat Rooms</h2>
            <ul>
                {rooms.map((room) => (
                    <li key={room.id}>
                        {room.name}
                        <button onClick={() => onRoomSelect(room.id)}>Enter</button>
                        <button onClick={() => handleLeaveRoom(room.id)}>Leave</button>
                    </li>
                ))}
            </ul>
            <input
                type="text"
                value={newRoomName}
                onChange={(e) => setNewRoomName(e.target.value)}
                placeholder="New room name"
            />
            <button onClick={handleCreateRoom}>Create Room</button>
        </div>
    );
};

export default ChatRoomList;
