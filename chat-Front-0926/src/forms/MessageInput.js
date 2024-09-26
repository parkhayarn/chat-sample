// src/forms/MessageInput.js
import React from 'react';

const MessageInput = ({ value, onChange, onSend }) => (
    <div className="message-input">
        <input type="text" value={value} onChange={onChange} placeholder="Enter your message" />
        <button onClick={onSend}>Send</button>
    </div>
);

export default MessageInput;
