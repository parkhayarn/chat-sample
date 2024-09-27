import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import ChatPage from './pages/ChatPage';
import RoomListPage from './pages/RoomListPage';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/chat/:roomId" element={<ChatPage />} />
        <Route path="/" element={<RoomListPage />} />
      </Routes>
    </Router>
  );
}

export default App;
