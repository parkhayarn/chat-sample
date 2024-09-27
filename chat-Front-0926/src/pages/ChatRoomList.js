import ChatRoomList from "../components/forms/ChatRoomListForm"

const ChatRoomList = ({ history }) => {
    const handleSelectRoom = (roomId) => {
        history.push(`/chat/${roomId}`);
    };

    return <ChatRoomList onSelectRoom={handleSelectRoom} />;
};

export default ChatRoomList;