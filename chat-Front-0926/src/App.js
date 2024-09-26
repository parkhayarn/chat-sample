import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import MainLayout from './layouts/MainLayout';
import ChatPage from './pages/ChatPage';
import RoomListPage from './pages/RoomListPage';

function App() {
  return (
    <Router>
      <MainLayout>
        <Switch>
          <Route path="/chat/:roomId" component={ChatPage} />
          <Route path="/" component={RoomListPage} />
        </Switch>
      </MainLayout>
    </Router>
  );
}

export default App;