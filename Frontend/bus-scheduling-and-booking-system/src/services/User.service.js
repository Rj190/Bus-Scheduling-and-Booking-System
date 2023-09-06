import axios from 'axios';

//http://localhost:8080/api/users
//http://192.168.45.123:8080/api/users
const API_URL = 'http://localhost:8080/api/users';


const getAllUsers = async () => {
  try {
    const response = await axios.get(`${API_URL}/list`);
  } catch (error) {
    console.error('Error fetching users:', error);
  }
};

const getUserById = async (id) => {
  try {
    const response = await axios.get(`${API_URL}/user-by/${id}`);
  } catch (error) {
    console.error('Error fetching user by ID:', error);
  }
};

const getUserByUserName = async (username) => {
  try {
    const response = await axios.get(`${API_URL}/user-by/username/${username}`);
    return response;
  } catch (error) {
    console.error('Error fetching user by ID:', error);
  }
};

const createUser = async (user) => {
  try {
    const response = await axios.post(`${API_URL}/create-user`, user);
    console.log(response);
    return response;
  } catch (error) {
    if (error.response) {
      // The request was made and the server responded with a status code
      // other than 2xx (e.g., 400 Bad Request).
      console.error('Error creating user:', error.response);
    } else if (error.request) {
      // The request was made but no response was received.
      console.error('Error creating user: No response received');
    } else {
      // Something happened in setting up the request that triggered an error.
      console.error('Error creating user:', error.message);
    }
    throw error;
  }
};

const updateUser = async (id, user) => {
  try {
    const response = await axios.put(`${API_URL}/update-user-by/${id}`, user);
    return response.data;
  } catch (error) {
    console.error('Error updating user:', error);
    throw error;
  }
};

const deleteUser = async (id) => {
  try {
    await axios.delete(`${API_URL}/delete-user-by/${id}`);
  } catch (error) {
    console.error('Error deleting user:', error);
    throw error;
  }
};

const getLoggedInUser = async () => {
  try {
    const response = await axios.get(`${API_URL}/current-user`);
    return response.data;
  } catch (error) {
    console.error('Error fetching logged-in user:', error);
    throw error;
  }
};

export default {
  getAllUsers,
  getUserById,
  createUser,
  updateUser,
  deleteUser,
  getLoggedInUser,
  getUserByUserName
};

