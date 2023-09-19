import axios from 'axios';

const BASE_URL = 'http://localhost:8080'; // Replace with your actual API base URL

const PassengerService = {
  getAllPassengers: async () => {
    try {
      const response = await axios.get(`${BASE_URL}/passengers`);
      return response;
    } catch (error) {
      console.error('Error getting passengers:', error);
      throw error;
    }
  },

  getPassengerById: async (id) => {
    try {
      const response = await axios.get(`${BASE_URL}/passengers/getpassengerbyid/${id}`);
      return response;
    } catch (error) {
      console.error('Error getting passenger by ID:', error);
      throw error;
    }
  },

  createPassenger: async (passenger) => {
    try {
      const response = await axios.post(`${BASE_URL}/passengers/createpassenger`, passenger);
      return response;
    } catch (error) {
      console.error('Error creating passenger:', error);
      throw error;
    }
  },

  updatePassengerById: async (id, passenger) => {
    try {
      const response = await axios.put(`${BASE_URL}/passengers/updatepassenegerbyid/${id}`, passenger);
      return response;
    } catch (error) {
      console.error('Error updating passenger by ID:', error);
      throw error;
    }
  },

  deletePassengerById: async (id) => {
    try {
      await axios.delete(`${BASE_URL}/passengers/deletepassengerbyid/${id}`);
    } catch (error) {
      console.error('Error deleting passenger by ID:', error);
      throw error;
    }
  },
};

export default PassengerService;
