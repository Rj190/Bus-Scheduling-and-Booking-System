import React from 'react';
import { Button } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import { useUser } from './UserContext';
import { IoIosLogOut } from 'react-icons/io';
import { Modal, Input, message } from 'antd';

const Logout = () => {
  const navigate = useNavigate();
  const { setUserRole, setJwtToken, setUserName, username ,setIsAuthenticated } = useUser();

  const logout = () => {
    // Clear user-related data from local storage
    localStorage.removeItem('jwtToken');
    localStorage.removeItem('userRole');
    localStorage.removeItem('username');

    // Clear user-related state in the context
    setUserRole(null);
    setJwtToken(null);
    setUserName(null);
    setIsAuthenticated(false);

    message.success('Logged out successfully!');

    localStorage.clear();
   
    // Redirect to the login page or any other desired page
    navigate('/login'); // You can update this route as needed
  };

  const handleLogout = () => {
    Modal.confirm({
      title: 'Are You Sure To Logout',
      content: (
        <div>
          <p>{username}</p>
        </div>
      ),
      onOk: logout, // Call the logout function when "OK" is clicked
    });
  };

  return (
    <li onClick={handleLogout}>
      <IoIosLogOut size={22} color="crimson" />
      <a>Logout</a>
    </li>
  );
};

export default Logout;
