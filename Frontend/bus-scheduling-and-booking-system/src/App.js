import React, { useContext, useEffect, useState } from 'react';
import { BrowserRouter, Route, Routes, useParams, Navigate, useLocation } from 'react-router-dom';
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
import { Modal, Input, message } from 'antd';
import Payment from './components/Payment';
import JourneyDetails from './components/home/JourneyDetails';
import Home from './components/home/Home';
import NavBar from './components/home/NavBar';
import MyBookings from './components/bookings/MyBookings';

function SeatSelectionWrapper() {
  const { journeyId } = useParams();
  return <Seats journeyId={journeyId} />;
}

function App() {
  const location = useLocation();
  const isAdminRoute = location.pathname.startsWith('/admin/');
  const isLoginRoute = location.pathname === '/login';
  const isRegisterRoute = location.pathname === '/reg';

  return (
    <UserProvider>
      {(!isAdminRoute && !isLoginRoute && !isRegisterRoute) && <NavBar />}
      <Routes>
        <Route path="/admin/*" element={<Sidebar />} />
        <Route path="/reg" element={<RegisterUserForm />} />
        <Route path="/login" element={<LoginForm />} />
        <Route path='/logout' element={<Logout />} />
        <Route path="/wallet" element={<Wallet />} />
        <Route path="/seats/:journeyId" element={<SeatSelectionWrapper />} />
        <Route path="/payment" element={<Payment />} />
        <Route path="/search" element={<HomeSearch />} />
        <Route path="/journey-details" element={<JourneyDetails />} />
        <Route path="/" element={<Home />} />
        <Route path="/bookings" element={<MyBookings/>} />
      </Routes>
    </UserProvider>
  );
}

export { App, SeatSelectionWrapper };
