import axios from 'axios';

const API_URL = 'http://localhost:8080/api';

export const sendMessage = (message) => {
    return axios.post(`${API_URL}/messages`, message);
};

export const getMessages = (roomId) => {
    return axios.get(`${API_URL}/messages/${roomId}`).then((response) => response.data);
};