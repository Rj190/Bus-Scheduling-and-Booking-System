import React, { useState,useEffect } from "react";
import Profile from "../images/profile.png";
import { BrowserRouter, Route, Routes, useParams } from 'react-router-dom';
import {
  FaClipboardList,
  FaBus,
  FaUser,
  FaMoneyBillWave,
  FaUserFriends,
  FaRoute,
  FaMapMarkerAlt,
  FaHandHoldingUsd,
  FaComment,
  FaBed,

} from "react-icons/fa";



import { useLocation, Link } from "react-router-dom";
import BusManagement from '../bus/BusManagement'
import RouteManagement from '../route/RouteManagement';
import JourneyManagement from '../Journey/JourneyManagement';

import { useUser } from '../UserContext';

import "../../css/main.scss";
import Welcome from "./Welcome";
import Logout from "../Logout";
import Wallet from "../Wallet";
import { useNavigate } from 'react-router-dom';
import { Modal, Input, message } from 'antd';

const Sidebar = () => {

  const { userRole, username ,jwtToken } = useUser();
  const navigate = useNavigate();;

  const location = useLocation();
  const [closeMenu, setCloseMenu] = useState(false);
  const handleCloseMenu = () => {
    setCloseMenu(!closeMenu);
  };




  if (!username) {
    // Show a popup if username is null
    Modal.error({
      title: 'Error',
      content: 'Please log in.',
      onOk: () => navigate('/login'), // Navigate to login page
    });
    return;
  }



  return (
    <>
      {userRole === 'Admin' && (
        <>
          <div className={closeMenu === false ? "sidebar" : "sidebar active"}>
            <div
              className={
                closeMenu === false ? "logoContainer" : "logoContainer active"
              }
            >
            </div>
            <div
              className={
                closeMenu === false ? "burgerContainer" : "burgerContainer active"
              }
            >
              <div
                className="burgerTrigger"
                onClick={() => {
                  handleCloseMenu();
                }}
              ></div>
              <div className="burgerMenu"></div>
            </div>
            <div
              className={
                closeMenu === false ? "profileContainer" : "profileContainer active"
              }
            >
              <img src={Profile} alt="profile" className="profile" />
              <div className="profileContents">
                <p className="name">Hello, {username} 👋</p>
              </div>
            </div>
            <div
              className={
                closeMenu === false ? "contentsContainer" : "contentsContainer active"
              }
            >
              <ul>
                <li className={location.pathname === "/admin/bus" ? "active" : ""}>
                  <FaBus />
                  <Link to="/admin/bus">bus</Link>
                </li>

                <li className={location.pathname === "/admin/route" ? "active" : ""}>
                  <FaRoute />
                  <Link to="/admin/route">route</Link>
                </li>

                <li className={location.pathname === "/admin/journey" ? "active" : ""}>
                  <FaMapMarkerAlt />
                  <Link to="/admin/journey">journey</Link>
                </li>

                <li className={location.pathname === "/admin/booking" ? "active" : ""}>
                  <FaClipboardList />
                  <Link to="/admin/booking">booking</Link>
                </li>

                <li className={location.pathname === "/admin/cancellation" ? "active" : ""}>
                  <FaHandHoldingUsd />
                  <Link to="/admin/cancellation">cancellation</Link>
                </li>

                {/* <li className={location.pathname === "/passenger" ? "active" : ""}>
            <FaUserFriends />
            <a href="/passenger">passenger</a>
          </li> */}
                <li className={location.pathname === "/admin/payment" ? "active" : ""}>
                  <FaMoneyBillWave />
                  <Link to="/admin/payment">payment</Link>
                </li>
                <li className={location.pathname === "/admin/user" ? "active" : ""}>
                  <FaUser />
                  <Link to="/admin/user">user</Link>
                </li>
                <li className={location.pathname === "/admin/feedback" ? "active" : ""}>
                  <FaComment />
                  <Link to="/admin/feedback">feedback</Link>
                </li>
                <li className={location.pathname === "/wallet" ? "active" : ""}>
                  <FaComment />
                  <Link to="/admin/wallet">Wallet</Link>
                </li>
                <Logout />
                
              </ul>
            </div>

          </div>
          <Routes>

            <Route path='/' element={<Welcome />} />
            <Route path='/bus' element={<BusManagement />} />
            <Route path='/route' element={<RouteManagement />} />
            <Route path='/journey' element={<JourneyManagement />} />
            <Route path="/wallet" element={<Wallet/>} />
          </Routes>

        </>
      )}

    </>

  );

};

export default Sidebar;
