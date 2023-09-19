// JourneyInfo.js
import React from 'react';

function JourneyInfo({ journey }) {
  return (
    <div>
      <p>Departure City: {journey.route.departureCity}</p>
      <p>Departure Date: {journey.journeyDate}</p>
      <p>Departure Time: {journey.journeyTime}</p>
      <p>Arrival City: {journey.route.arrivalCity}</p>
      <p>Arrival Date: {journey.arrivalDate}</p>
      <p>Arrival Time: {journey.arrivalTime}</p>
    </div>
  );
}

export default JourneyInfo;
