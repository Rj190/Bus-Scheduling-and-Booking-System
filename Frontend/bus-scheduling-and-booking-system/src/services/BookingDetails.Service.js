import axios from 'axios';

const BASE_URL = 'http://localhost:8080'; // Replace with your actual base URL

const BookingDetailsService = {
  getAllBookingDetails: async () => {
    try {
      const response = await axios.get(`${BASE_URL}/api/booking-details/all`);
      return response;
    } catch (error) {
      console.error('Error fetching booking details:', error);
      throw error;
    }
  },

  getBookingDetailsById: async (id) => {
    try {
      const response = await axios.get(`${BASE_URL}/api/booking-details/${id}`);
      return response;
    } catch (error) {
      console.error('Error fetching booking details by ID:', error);
      throw error;
    }
  },

  createBookingDetails: async (bookingId, seatId, passengerId) => {
    try {
      const response = await axios.post(
        `${BASE_URL}/api/booking-details/${bookingId}/${seatId}/${passengerId}`);
      return response;
    } catch (error) {
      console.error('Error creating booking details:', error);
      throw error;
    }
  },

  updateBookingDetails: async (id, bookingDetails) => {
    try {
      const response = await axios.put(`${BASE_URL}/api/booking-details/${id}`, bookingDetails);
      return response;
    } catch (error) {
      console.error('Error updating booking details:', error);
      throw error;
    }
  },

  deleteBookingDetails: async (id) => {
    try {
      await axios.delete(`${BASE_URL}/api/booking-details/${id}`);
    } catch (error) {
      console.error('Error deleting booking details:', error);
      throw error;
    }
  },

  getBookingDetailsByBookingId : async (bookingId) => {
    try {
       const response =  await axios.get(`${BASE_URL}/api/booking-details/getbybookingid/${bookingId}`);
        return response;
      } catch (error) {
        console.error('Error deleting booking details:', error);
        throw error;
      }
    },

};




export default BookingDetailsService;
