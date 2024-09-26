import React from 'react';
import ChatRoomList from '../components/ChatRoomList';

const RoomListPage = ({ history }) => {
    const handleSelectRoom = (roomId) => {
        history.push(`/chat/${roomId}`);
    };

    return <ChatRoomList onSelectRoom={handleSelectRoom} />;
};

export default RoomListPage;