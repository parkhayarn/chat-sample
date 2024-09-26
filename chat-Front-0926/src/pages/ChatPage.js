import React from 'react';
import ChatRoom from '../components/ChatRoom';

const ChatPage = ({ match }) => {
    const { roomId } = match.params;
    return <ChatRoom roomId={roomId} />;
};

export default ChatPage;