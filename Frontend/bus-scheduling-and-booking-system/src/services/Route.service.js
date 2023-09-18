import axios from 'axios';

const API_URL = 'http://localhost:8080/routes'; // Replace with your API URL

const getAllRoutes = async () => {
  try {
    const response = await axios.get(`${API_URL}/getallroutes`);
    return response;
  } catch (error) {
    console.error('Error fetching routes:', error);
    throw error;
  }
};

const getRouteById = async (routeId) => {
  try {
    const response = await axios.get(`${API_URL}/getroutebyid/${routeId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching route by ID:', error);
    throw error;
  }
};

const createRoute = async (route) => {
  try {
    const response = await axios.post(`${API_URL}/createroute`, route);
    return response;
  } catch (error) {
    console.error('Error creating route:', error);
    throw error;
  }
};

const updateRoute = async (routeId, updatedRoute) => {
  try {
    const response = await axios.put(`${API_URL}/updateroute/${routeId}`, updatedRoute);
    return response;
  } catch (error) {
    console.error('Error updating route:', error);
    throw error;
  }
};

const deleteRoute = async (routeId) => {
  try {
    await axios.delete(`${API_URL}/deleteroute/${routeId}`);
  } catch (error) {
    console.error('Error deleting route:', error);
    throw error;
  }
};

export default {
  getAllRoutes,
  getRouteById,
  createRoute,
  updateRoute,
  deleteRoute,
};
