import axios from 'axios';

const BASE_URL = 'http://localhost:8080'; // Replace with your actual API base URL

const BookingService = {
  getAllBookings: async () => {
    try {
      const response = await axios.get(`${BASE_URL}/api/booking/all`);
      return response;
    } catch (error) {
      console.error('Error getting all bookings:', error);
      throw error;
    }
  },

  saveBooking: async (username, journeyId, paymentId, booking) => {
    try {
      const response = await axios.post(`${BASE_URL}/api/booking/add/${username}/${journeyId}/${paymentId}`, booking);
      return response;
    } catch (error) {
      console.error('Error saving booking:', error);
      throw error;
    }
  },

  updateBooking: async (booking, bookingId) => {
    try {
      const response = await axios.put(`${BASE_URL}/api/booking/updatebooking/${bookingId}`, booking);
      return response;
    } catch (error) {
      console.error('Error updating booking:', error);
      throw error;
    }
  },

  getBookingById: async (bookingId) => {
    try {
      const response = await axios.get(`${BASE_URL}/api/booking/findbooking/${bookingId}`);
      return response;
    } catch (error) {
      console.error('Error getting booking by ID:', error);
      throw error;
    }
  },

  deleteBookingById: async (bookingId) => {
    try {
      await axios.delete(`${BASE_URL}/api/booking/deletebooking/${bookingId}`);
    } catch (error) {
      console.error('Error deleting booking by ID:', error);
      throw error;
    }
  },
};

export default BookingService;
