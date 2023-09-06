import axios from 'axios';

const API_URL = 'http://localhost:8080/wallets';

const getAllWallets = async () => {
  try {
    const response = await axios.get(API_URL);
    return response.data;
  } catch (error) {
    console.error('Error fetching wallets:', error);
    throw error;
  }
};

const getWalletById = async (id) => {
  try {
    const response = await axios.get(`${API_URL}/${id}`);
    return response;
  } catch (error) {
    console.error('Error fetching wallet by ID:', error);
    throw error;
  }
};

const createWallet = async (username) => {
  try {
    const response = await axios.post(`${API_URL}/createwallet/${username}`);
    console.log(response);
    return response.data;
  } catch (error) {
    if (error.response) {
      // The request was made and the server responded with a status code
      // other than 2xx (e.g., 400 Bad Request).
      console.error('Error creating wallet:', error.response);
    } else if (error.request) {
      // The request was made but no response was received.
      console.error('Error creating wallet: No response received');
    } else {
      // Something happened in setting up the request that triggered an error.
      console.error('Error creating wallet:', error.message);
    }
    throw error;
  }
};

const updateWallet = async (id, wallet) => {
  try {
    const response = await axios.put(`${API_URL}/${id}`, wallet);
    return response;
  } catch (error) {
    console.error('Error updating wallet:', error);
    throw error;
  }
};

const deleteWallet = async (id) => {
  try {
    await axios.delete(`${API_URL}/${id}`);
  } catch (error) {
    console.error('Error deleting wallet:', error);
    throw error;
  }
};

export default {
  getAllWallets,
  getWalletById,
  createWallet,
  updateWallet,
  deleteWallet,
};
