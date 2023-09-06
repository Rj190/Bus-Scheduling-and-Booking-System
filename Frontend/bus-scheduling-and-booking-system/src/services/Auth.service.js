import { useState, useEffect } from 'react';
import axios from 'axios';

//http://localhost:8080/auth
//http://192.168.45.123:8080/auth
const API_URL = 'http://localhost:8080/auth'; // Replace with your authentication endpoint URL



  const login = (credentials) => {
    
    const response = axios .post(`${API_URL}/login`,credentials)
    console.log(response)
    return response;
     
  };

  const logout = () => {
    localStorage.removeItem('user');
  };

  const getCurrentUser = () => {
   // return user;
  };

  export default {
    login,
    logout,
    getCurrentUser,
  };

