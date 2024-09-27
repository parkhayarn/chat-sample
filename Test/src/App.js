import React, { useState } from 'react';
import ChatRoom from './components/ChatRoom';
import ChatRoomList from './components/ChatRoomList';

function App() {

  console.log("App component rendered");
  
  const [selectedRoom, setSelectedRoom] = useState(null);

  return (
    <div>
      <ChatRoomList onRoomSelect={setSelectedRoom} />
      {selectedRoom && <ChatRoom roomId={selectedRoom} />}
    </div>
  );
}

export default App;