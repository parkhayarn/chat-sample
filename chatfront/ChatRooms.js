import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ChatRoom from './ChatRoom';  // 채팅방 컴포넌트

const ChatRooms = () => {
const [rooms, setRooms] = useState([]);
const [newRoomName, setNewRoomName] = useState('');
const [selectedRoom, setSelectedRoom] = useState(null);

useEffect(() => {
    const fetchRooms = async () => {
    const response = await axios.get('http://localhost:8080/rooms');
    setRooms(response.data);
    };
    fetchRooms();
}, []);

const createRoom = async () => {
    if (newRoomName.trim()) {
    const response = await axios.post('http://localhost:8080/rooms', newRoomName, {
        headers: { 'Content-Type': 'application/json' }
    });
    setRooms([...rooms, response.data]);
    setNewRoomName('');
    }
};

return (
    <div>
    <h2>Chat Rooms</h2>
    <input
        type="text"
        value={newRoomName}
        onChange={(e) => setNewRoomName(e.target.value)}
        placeholder="Enter room name"
    />
    <button onClick={createRoom}>Create Room</button>

    <ul>
        {rooms.map((room) => (
        <li key={room.id} onClick={() => setSelectedRoom(room)}>
            {room.roomName}
        </li>
        ))}
    </ul>

    {selectedRoom && <ChatRoom room={selectedRoom} />}
    </div>
);
};

export default ChatRooms;
