// RouteList.js
import React from 'react';
import '../../css/route.css';

function RouteList({ routes, onEdit }) {
  return (
    <div className="route-list-container">
      <h1 className="reo">Route List</h1>
      <table className="route-table">
        <thead>
          <tr>
            <th>Departure City</th>
            <th>Arrival City</th>
            <th>Duration</th>
            <th>Distance</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {routes.map((route) => (
            <tr key={route.routeId}>
              <td>{route.departureCity}</td>
              <td>{route.arrivalCity}</td>
              <td>{route.duration} Hrs</td>
              <td>{route.distance} KM</td>
              <td>
                <button onClick={() => onEdit(route)} className="routeedit">
                  Edit
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default RouteList;
