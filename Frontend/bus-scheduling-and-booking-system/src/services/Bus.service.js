import axios from 'axios';

const API_URL = 'http://localhost:8080/api/bus'; // Replace with your API URL

const getAllBuses = async () => {
    try {
        const response = await axios.get(`${API_URL}/listbuses`);
        return response;
    } catch (error) {
        console.error('Error fetching routes:', error);
        throw error;
    }
};

const getBusById = async (busId) => {
    try {
        const response = await axios.get(`${API_URL}/findbus/${busId}`);
        return response;
    } catch (error) {
        console.error('Error fetching route by ID:', error);
        throw error;
    }
};

const createBus = async (bus) => {
    try {
        const response = await axios.post(`${API_URL}/savebus`, bus);
        return response;
    } catch (error) {
        console.error('Error creating route:', error);
        throw error;
    }
};

const updateBus = async (busId, updatedBus) => {
    try {
        const response = await axios.put(`${API_URL}/updatebus/${busId}`, updatedBus);
        return response;
    } catch (error) {
        console.error('Error updating route:', error);
        throw error;
    }
};

const deleteBus = async (busId) => {
    try {
        await axios.delete(`${API_URL}/deletebus/${busId}`);
    } catch (error) {
        console.error('Error deleting route:', error);
        throw error;
    }
};

export default {
    createBus,
    deleteBus,
    getAllBuses,
    getBusById,
    updateBus,
};
