import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import './JourneyDetails.css'; // Import the CSS file

function JourneyDetails() {
  const { state } = useLocation();
  const navigate = useNavigate();

  const toggleSeatSelection = (journeyId) => {
    navigate(`/seats/${journeyId}`);
  };

  return (
    <div className="journey-details-container">
      <h2>Journey Details</h2>
      {state && state.journeys ? (
        state.journeys.map((journey) => (
          <div key={journey.journeyId} className="journey-card">
            <p>{journey.journeyTime} - {journey.arrivalTime}</p>
            <p>{journey.route.duration} hrs</p>
            <p>{journey.bus.busType}</p>
            <p>{journey.route.departureCity} - {journey.route.arrivalCity}</p>
            <p>Fare: {journey.fare}</p>
            {journey.bus.seats.length > 0 && (
              <p className="seats-available">
                Seats Available: {journey.bus.seats.filter(seat => seat.seatAvailabilityStatus === "Available").length}
              </p>
            )}
            <button className="payment-button" onClick={() => toggleSeatSelection(journey.journeyId)}>View Seats</button>
          </div>
        ))
      ) : (
        <p>No journey data available.</p>
      )}
    </div>
  );
}

export default JourneyDetails;
