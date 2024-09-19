import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';

const ChatWindow = () => {
  const { userId } = useParams(); // useParams 훅을 사용하여 URL에서 userId 가져오기
  const [messages, setMessages] = useState([]); // 채팅 메시지 상태 관리
  const [messageInput, setMessageInput] = useState(''); // 입력된 메시지 상태 관리
  const [webSocket, setWebSocket] = useState(null); // WebSocket 객체 상태 관리
  const navigate = useNavigate(); // useNavigate 훅을 사용하여 페이지 이동을 처리할 함수 가져오기
  const [userIds, setUserIds] = useState([]);

  useEffect(() => {
    // WebSocket 연결 설정
    const wsProtocol = window.location.protocol === 'https:' ? 'wss://' : 'ws://'; // 프로토콜 설정
    const wsUrl = `${wsProtocol}localhost:8081/ChattingServer`; // WebSocket 서버 URL 설정
    const ws = new WebSocket(wsUrl); // WebSocket 객체 생성

    // WebSocket 이벤트 핸들러 설정
    ws.onopen = () => {
      console.log('WebSocket 연결 성공');
      const initialMessage = 'WebSocket 서버에 연결되었습니다.<br>';
      setMessages(prevMessages => [...prevMessages, { type: 'info', message: initialMessage }]);
      ws.send(JSON.stringify({ type: 'join', userId })); // 사용자 입장 메시지 전송
      setUserIds(prevUserIds => [...prevUserIds, userId]); // 2. userIds 배열에 userId 추가
    };

    ws.onmessage = (event) => {
      console.log('서버로부터 메시지 수신:', event.data);
      const message = event.data;
      setMessages(prevMessages => [...prevMessages, { type: 'received', message }]);
    };

    ws.onclose = () => {
      console.log('WebSocket 서버와 연결이 종료 되었습니다.');
      const closingMessage = 'WebSocket 서버와 연결이 종료 되었습니다.<br>';
      setMessages(prevMessages => [...prevMessages, { type: 'info', message: closingMessage }]);
    };

    ws.onerror = (error) => {
      console.error('WebSocket error:', error);
    };

    setWebSocket(ws);

    return () => {
          // 컴포넌트 언마운트 시 WebSocket 연결 해제 및 leave 메시지 전송
      if (ws) {
        ws.send(JSON.stringify({ type: 'leave', userId })); // 사용자 퇴장 메시지 전송
        ws.close();
      }
    };
  }, []); // 처음 한 번만 실행되도록 빈 배열을 두 번째 인자로 전달

  const sendMessage = () => {
    if (webSocket && webSocket.readyState === WebSocket.OPEN) {
      const trimmedMessage = messageInput.trim();
      if (trimmedMessage !== '') {
        webSocket.send(JSON.stringify({ type: 'message', userId, message: trimmedMessage })); // 사용자 ID와 메시지 전송
        setMessages(prevMessages => [...prevMessages, { type: 'sent', message: trimmedMessage }]);
        setMessageInput('');
      }
    } else {
      console.error('WebSocket 연결이 열리지 않았습니다.');
    }
  };

  const handleKeyPress = (event) => {
    if (event.key === 'Enter') {
      sendMessage(); // Enter 키를 눌렀을 때 sendMessage 함수 호출
    }
  };

  const handleBackToMain = () => {
    navigate('/'); // 메인 페이지로 이동
  };

  return (
    <div className="chat-container">
      <h2>WebSocket 채팅 </h2>

      <div
        id="chatWindow"
        className="chat-window"
        style={{
          border: '1px solid #ccc',
          width: '380px',
          height: '400px',
          overflowY: 'scroll',
          padding: '10px',
          backgroundColor: '#fff',
        }}
      >
        {messages.map((msg, index) => (
          <div
            key={index}
            className={`message ${msg.type}`}
            style={{
              textAlign: msg.type === 'sent' ? 'right' : 'left',
              marginTop: '5px',
              color: msg.type === 'sent' ? 'blue' : '#f32f00',
            }}
            dangerouslySetInnerHTML={{ __html: msg.message }}
          />
        ))}
      </div>
      <div className="input-container">
        <input
          type="text"
          id="chatMessage"
          placeholder="메시지 입력"
          value={messageInput}
          onChange={(e) => setMessageInput(e.target.value)}
          onKeyDown={handleKeyPress}
        />
        <button id="sendBtn" onClick={sendMessage}>
          전송
        </button>
      </div>
      <button onClick={handleBackToMain}>
        메인으로 돌아가기
      </button>
      
    </div>
  );
};

export default ChatWindow;
