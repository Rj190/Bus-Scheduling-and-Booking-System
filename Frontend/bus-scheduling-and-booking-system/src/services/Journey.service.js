import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/journeys'; // Adjust this to match your API endpoint

const getAllJourneys = async () => {
  try {
    const response = await axios.get(`${API_BASE_URL}/all`);
    return response.data;
  } catch (error) {
    console.error('Error fetching journeys:', error);
    throw error;
  }
};

const addJourney = async (journey, busId, routeId) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/add/${busId}/${routeId}`, journey);
    return response;
  } catch (error) {
    console.error('Error adding journey:', error.response.data);
    throw error.response.data;
  }
};

const getJourneyById = async (journeyId) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/${journeyId}`);
    return response;
  } catch (error) {
    console.error('Error fetching journey by ID:', error);
    throw error;
  }
};

const deleteJourney = async (journeyId) => {
  try {
    await axios.delete(`${API_BASE_URL}/delete/${journeyId}`);
  } catch (error) {
    console.error('Error deleting journey:', error);
    throw error;
  }
};

const updateJourney = async (journeyId,busId,routeId, newJourney) => {
  try {
    const response = await axios.put(`${API_BASE_URL}/update/${journeyId}/${busId}/${routeId}`, newJourney);
    return response;
  } catch (error) {
    console.error('Error updating journey:', error);
    throw error;
  }
};

const getJourneysByDate = async (journeyDate) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/all/by-journey-date/${journeyDate}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching journeys by date:', error);
    throw error;
  }
};

const getJourneysByTime = async (startTime, endTime) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/all/by-journey-time/${startTime}/${endTime}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching journeys by time:', error);
    throw error;
  }
};

const getJourneysByBus = async (busId) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/by-bus/${busId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching journeys by bus:', error);
    throw error;
  }
};

const getJourneysByRoute = async (routeId) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/by-route/${routeId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching journeys by route:', error);
    throw error;
  }
};

export default {
  addJourney,
  deleteJourney,
  getAllJourneys,
  getJourneyById,
  updateJourney,
  getJourneysByDate,
  getJourneysByTime,
  getJourneysByBus,
  getJourneysByRoute,
};
