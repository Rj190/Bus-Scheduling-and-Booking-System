import axios from 'axios';

 

const API_URL = 'http://localhost:8080/payment'; // Replace with your API URL

 

const viewAllPayments = async () => {

  try {

    const response = await axios.get(`${API_URL}/viewallpayments`);

    return response;

  } catch (error) {

    console.error('Error fetching payments:', error);

    throw error;

  }

};

 

const viewPaymentById = async (payId) => {  

  try {

    const response = await axios.get(`${API_URL}/getpaymentsbyid/${payId}`);

    return response.data;

  } catch (error) {

    console.error('Error fetching payment by ID:', error);

    throw error;

  }

};

 

const insertPayment = async (username,payement) => {        

  try {

    const response = await axios.post(`${API_URL}/insertpatment/${username}`, payement);

    return response;

  } catch (error) {

    console.error('Error creating payement:', error);

    throw error;

  }

};

 

const updatePaymentById = async (paymentId, newPayment) => {

  try {

    const response = await axios.put(`${API_URL}/updatepaymentsbyid/${paymentId}`, newPayment);

    return response;

  } catch (error) {

    console.error('Error updating payment:', error);

    throw error;

  }

};

 

const deletePaymentById = async (payId) => {  

  try {

    await axios.delete(`${API_URL}/deletepayment/${payId}`);

  } catch (error) {

    console.error('Error deleting payment:', error);

    throw error;

  }

};

 

export default {

    viewAllPayments,

    viewPaymentById,

    insertPayment,

    updatePaymentById,

    deletePaymentById

 

};