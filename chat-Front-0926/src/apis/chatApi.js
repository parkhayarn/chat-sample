import axios from 'axios';

const API_URL = 'http://localhost:8080/api/chat';

export const fetchChatRooms = async () => {
    const response = await axios.get(`${API_URL}/rooms`);
    return response.data;
};

export const createChatRoom = async (name) => {
    const response = await axios.post(`${API_URL}/room`, name, {
        headers: { 'Content-Type': 'application/json' },
    });
    return response.data;
};

export const fetchChatMessages = async (roomId) => {
    const response = await axios.get(`${API_URL}/messages/${roomId}`);
    return response.data;
};

export const sendMessage = async (roomId, message) => {
    const response = await axios.post(`${API_URL}/sendMessage/${roomId}`, { message });
    return response.data;
};
