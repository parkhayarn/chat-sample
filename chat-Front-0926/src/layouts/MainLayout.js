import React from 'react';

const MainLayout = ({ children }) => (
    <div className="main-layout">
        <header>
            <h1>Chat Application</h1>
        </header>
        <main>{children}</main>
        <footer>Footer Content</footer>
    </div>
);

export default MainLayout;
