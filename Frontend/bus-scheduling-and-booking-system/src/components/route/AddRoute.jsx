import React, { useState } from 'react';
import { Modal, Input, Button, message, Spin, AutoComplete } from 'antd';
import '../../css/route.css';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import RouteService from '../../services/Route.service';
import axios from 'axios';

function AddRoute({ onAddRoute, onCancel }) {
  const [calculating, setCalculating] = useState(false); // State to track calculation progress

  // Initial form values
  const initialValues = {
    departureCity: '',
    arrivalCity: '',
    distance: '',
    duration: '',
  };


  // Validation schema using Yup
  const validationSchema = Yup.object().shape({
    departureCity: Yup.string()
      .required('Departure city is required')
      .min(2, 'Departure city must be at least 2 characters')
      .max(100, 'Departure city must be at most 100 characters'),
    arrivalCity: Yup.string()
      .required('Arrival city is required')
      .min(2, 'Arrival city must be at least 2 characters')
      .max(100, 'Arrival city must be at most 100 characters'),
  });

  const formik = useFormik({
    initialValues,
    validationSchema,
    onSubmit: async (values) => {
      try {
        setCalculating(true); // Start calculation, show progress window

        // Fetch coordinates for departure city
        const departureCoordinates = await getCoordinates(values.departureCity);

        // Fetch coordinates for arrival city
        const arrivalCoordinates = await getCoordinates(values.arrivalCity);

        // Calculate distance and duration using coordinates
        const distanceDuration = await calculateDistanceAndDuration(
          departureCoordinates,
          arrivalCoordinates
        );

        const updatedValues = {
          ...values,
          distance: distanceDuration.distance,
          duration: distanceDuration.duration,
        };

        // Set the formik values after the calculation is complete
        formik.setValues(updatedValues);
        
        console.log(updatedValues)
        Modal.confirm({
          title: 'Confirm Data Entry',
          content: (
            <div>
              <p>Departure City: {values.departureCity}</p>
              <p>Arrival City: {values.arrivalCity}</p>
              <p>Distance: {distanceDuration.distance} km</p>
              <p>Duration: {distanceDuration.duration} hours</p>
            </div>
          ),
          onOk: async () => sendDataToServer(updatedValues),
        });
      } catch (error) {
        console.error('Error fetching route data:', error);
        message.error('An error occurred while fetching route data: ' + error.message);
      } finally {
        setCalculating(false); // Calculation is done, close progress window
      }
    },
  });

  // Function to fetch coordinates for a city name
  async function getCoordinates(cityName) {
    try {
      const response = await axios.get(
        `https://api.openrouteservice.org/geocode/search?api_key=5b3ce3597851110001cf6248f5b9c2b73deb4f2ba3f42f36ee988721&text=${encodeURIComponent(
          cityName
        )}`
      );

      if (response.data && response.data.features && response.data.features.length > 0) {
        const coordinates = response.data.features[0].geometry.coordinates;
        return coordinates;
      } else {
        throw new Error('City not found.');
      }
    } catch (error) {
      console.error('Error fetching coordinates:', error);
      throw error;
    }
  }

  // Function to calculate distance and duration using coordinates
  async function calculateDistanceAndDuration(departureCoordinates, arrivalCoordinates) {
    try {
      const apiKey = '5b3ce3597851110001cf6248f5b9c2b73deb4f2ba3f42f36ee988721'; // Replace with your API key

      const response = await axios.get(
        `https://api.openrouteservice.org/v2/directions/driving-car?api_key=${apiKey}&start=${departureCoordinates[0]},${departureCoordinates[1]}&end=${arrivalCoordinates[0]},${arrivalCoordinates[1]}`
      );

      if (response.data && response.data.features && response.data.features.length > 0) {
        console.log(response.data.features)
        const route = response.data.features[0];
        const distance = route.properties.segments[0].distance / 1000; // Distance in kilometers
        const duration = route.properties.segments[0].duration / 3600; // Duration in hours

        return { distance, duration };
      } else {
        throw new Error('No route found.');
      }
    } catch (error) {
      console.error('Error calculating distance and duration:', error);
      throw error;
    }
  }

  const sendDataToServer = async (values) => {
    try {
      const response = await RouteService.createRoute(values);
      onAddRoute(response.data); // Trigger a callback to update the UI with the new route

      if (response.status === 201) {
        message.success('Route added successfully!');
        formik.resetForm();
      } else {
        message.error('Failed to create route');
      }
    } catch (error) {
      console.error('Error creating route:', error);
      message.error('An error occurred while creating the route zzz.');
    }
  };

  return (
    <Modal
      open={true}
      title="Add Route"
      onCancel={onCancel}
      footer={[
        <Button key="cancel" className="btn btn-secondary" onClick={onCancel}>
          Cancel
        </Button>,
        <Button
          key="save"
          className="btn btn-primary"
          onClick={formik.handleSubmit}
        >
          Save
        </Button>,
      ]}
    >
      <div className="form-container">
        <form onSubmit={formik.handleSubmit}>
          <div className="form-group">
            <Input
              type="text"
              name="departureCity"
              placeholder="Departure City"
              value={formik.values.departureCity}
              onChange={formik.handleChange}
            />
            {formik.touched.departureCity && formik.errors.departureCity ? (
              <div className="validation-error">
                {formik.errors.departureCity}
              </div>
            ) : null}
          </div>
          <div className="form-group">
            <Input
              type="text"
              name="arrivalCity"
              placeholder="Arrival City"
              value={formik.values.arrivalCity}
              onChange={formik.handleChange}
            />
            {formik.touched.arrivalCity && formik.errors.arrivalCity ? (
              <div className="validation-error">
                {formik.errors.arrivalCity}
              </div>
            ) : null}
          </div>
          {calculating && (
            <div className="progress-window">
              <Spin size="small"/> Calculating distance and duration...
              
            </div>
          )}
        </form>
      </div>
    </Modal>
  );
}

export default AddRoute;
