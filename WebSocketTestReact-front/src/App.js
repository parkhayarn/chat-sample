import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Main from './pages/Main'; // 예시에서는 Main 컴포넌트 경로
import ChatWindow from './pages/ChatWindow'; // 예시에서는 ChatWindow 컴포넌트 경로

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/chatWindow/:userId" element={<ChatWindow />} />
      </Routes>
    </Router>
  );
};

export default App;
