// CreateUser.service.js
import axios from 'axios';

const API_BASE_URL = 'http://your-api-base-url'; // Replace with your actual API base URL

const CreateUser = {
  createUser: async (userData) => {
    try {
      const response = await axios.post(`${API_BASE_URL}/create-user`, userData);
      return response.data;
    } catch (error) {
      throw error;
    }
  },
};

export default CreateUser;
