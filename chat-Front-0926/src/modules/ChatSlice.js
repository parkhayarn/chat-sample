import { createSlice } from '@reduxjs/toolkit';

export const chatSlice = createSlice({
    name: 'chat',
    initialState: {
        rooms: [],
        messages: {},
    },
    reducers: {
        setRooms: (state, action) => {
            state.rooms = action.payload;
        },
        addMessage: (state, action) => {
            const { roomId, message } = action.payload;
            if (!state.messages[roomId]) {
                state.messages[roomId] = [];
            }
            state.messages[roomId].push(message);
        },
    },
});

export const { setRooms, addMessage } = chatSlice.actions;
export default chatSlice.reducer;
