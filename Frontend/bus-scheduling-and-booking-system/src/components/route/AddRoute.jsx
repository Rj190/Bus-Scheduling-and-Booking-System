import React, { useState } from 'react';
import { v4 as uuidv4 } from 'uuid';
import '../../css/route.css'


function AddRoute({ onAddRoute }) {
  const [route, setRoute] = useState({
    id: '',
    departureCity: '',
    arrivalCity: '',
    distance: '',
    duration: '',
    fare: '',
  });

  const cities = ['City A', 'City B', 'City C', 'City D'];

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setRoute({
      ...route,
      [name]: value,
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    // Check if departureCity and arrivalCity are not empty
    if (!route.departureCity || !route.arrivalCity) {
      alert('Departure and Arrival cities are required.');
      return;
    }

    const newRoute = { ...route, id: uuidv4() };
    onAddRoute(newRoute);
    setRoute({
      id: '',
      departureCity: '',
      arrivalCity: '',
      distance: '',
      duration: '',
      fare: '',
    });
  };

  return (
    <div className="form-container">
      <h2>Add Route</h2>
      <form onSubmit={handleSubmit}>
        <select
          className="form-input"
          name="departureCity"
          value={route.departureCity}
          onChange={handleInputChange}
        >
          <option value="">Select Departure City</option>
          {cities.map((city) => (
            <option key={city} value={city}>
              {city}
            </option>
          ))}
        </select>
        <select
          className="form-input"
          name="arrivalCity"
          value={route.arrivalCity}
          onChange={handleInputChange}
        >
          <option value="">Select Arrival City</option>
          {cities.map((city) => (
            <option key={city} value={city}>
              {city}
            </option>
          ))}
        </select>
        <input
          type="number"
          name="distance"
          className="form-input"
          placeholder="Distance (in km)"
          value={route.distance}
          onChange={handleInputChange}
        />
        <input
          type="number"
          name="duration"
          className="form-input"
          placeholder="Duration (in hours)"
          value={route.duration}
          onChange={handleInputChange}
        />
        <input
          type="number"
          name="fare"
          className="form-input"
          placeholder="Fare (in Rs)"
          value={route.fare}
          onChange={handleInputChange}
        />
        <button type="submit" className="form-button">
          Add
        </button>
      </form>
      {/* Display added route details */}
      {route.id && (
        <div className="added-route-details">
          <h3>Added Route Details:</h3>
          <p>Departure City: {route.departureCity}</p>
          <p>Arrival City: {route.arrivalCity}</p>
          <p>Distance: {route.distance} km</p>
          <p>Duration: {route.duration} hours</p>
          <p>Fare: ${route.fare}</p>
        </div>
      )}
    </div>
  );
}

export default AddRoute;