import React from 'react';
import '../../css/Journey.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEdit, faExclamationCircle } from '@fortawesome/free-solid-svg-icons';

function JourneyList({ journeys, onEdit }) {
    return (
        <div className="bus-list-container">
            <h1 className="reo">Journey List</h1>
            {journeys.length === 0 ? (
                <div className="no-data-message">
                    <p>No data available.</p>
                    <FontAwesomeIcon icon={faExclamationCircle} size="5x" color="red" />
                </div>
            ) : (
                <table className="bus-table">
                    <thead>
                        <tr>
                            <th>Journey Date</th>
                            <th>Journey Time</th>
                            <th>Arrival Date</th>
                            <th>Arrival Time</th>
                            <th>Bus Reg</th>
                            <th>Route</th>
                            <th>Fare</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {journeys.map((journey) => (
                            <tr key={journey.journeyId}>
                                <td>{journey.journeyDate}</td>
                                <td>{journey.journeyTime}</td>
                                <td>{journey.arrivalDate}</td>
                                <td>{journey.arrivalTime}</td>
                                <td>{journey.bus.busRegistrationNumber}</td>
                                <td>
                                    {journey.route.departureCity} - {journey.route.arrivalCity}
                                </td>
                                <td>{journey.fare}</td>
                                <td>
                                    <button onClick={() => onEdit(journey)} className="buseedit">
                                        <FontAwesomeIcon icon={faEdit} /> Edit
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
}

export default JourneyList;
