// src/apis/chatApi.js
import axios from 'axios';

const API_URL = 'http://localhost:8080/api/chat';

// 채팅방 목록 조회
export const fetchChatRooms = async () => {
    const response = await axios.get(`${API_URL}/rooms`);
    return response.data;
};

// 채팅방 생성
export const createChatRoom = async (name) => {
    const response = await axios.post(`${API_URL}/room`, { name }, {
        headers: { 'Content-Type': 'application/json' }
    });
    return response.data;
};

// 특정 채팅방 메시지 조회
export const fetchChatMessages = async (roomId) => {
    const response = await axios.get(`${API_URL}/messages/${roomId}`);
    return response.data;
};

// 메시지 전송
export const sendMessage = async (roomId, message) => {
    const response = await axios.post(`${API_URL}/sendMessage/${roomId}`, { message });
    return response.data;
};
