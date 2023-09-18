import React, { useContext, useEffect } from 'react';
import { BrowserRouter, Route, Routes, useParams, Navigate } from 'react-router-dom';
import './App.css';
import LoginForm from './components/Login';
import RegisterUserForm from './components/RegisterUserFrom';
import Wallet from './components/Wallet';
import BusManagement from './components/bus/BusManagement';
import RouteManagement from './components/route/RouteManagement';
import Seats from './components/seat/SelectSeat';
import HomeSearch from './components/home/HomeSearch';
import AddJourney from './components/Journey/AddJourney';
import JourneyManagement from './components/Journey/JourneyManagement';
import SeatSelection from './components/seat/SelectSeat';
import Sidebar from './components/sidebar/Sidebar';
import { UserProvider, useUser } from './components/UserContext';
import Logout from './components/Logout';
import { useNavigate } from 'react-router-dom';
import { Modal, Input, message } from 'antd';


function App() {



  return (
    <BrowserRouter>
      <UserProvider>

        <Routes>
          <Route path="/admin/*" element={<Sidebar />} />
          <Route path="/reg" element={<RegisterUserForm />} />
          <Route path="/login" element={<LoginForm />} />
          <Route path='/logout' element={<Logout />} />
          <Route path="/wallet" element={<Wallet />} />
          <Route path="/seats/:id" element={<SeatSelectionWrapper />} />
        </Routes>
      </UserProvider>
    </BrowserRouter>

  );
}


function SeatSelectionWrapper() {
  const { id } = useParams();
  return <Seats id={id} />;
}


export default App;
