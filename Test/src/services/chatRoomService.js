import axios from 'axios';

const API_URL = 'http://localhost:8080/chat';

export const getChatRooms = () => {
    return axios.get(API_URL).then((response) => response.data);
};

export const createChatRoom = (room) => {
    return axios.post(API_URL, room).then((response) => response.data);
};

export const leaveChatRoom = (roomId) => {
    return axios.delete(`${API_URL}/${roomId}`);
};