import React from 'react';
import '../../css/route.css'

function RouteList({ routes, onEdit, onDelete }) {
  return (
    <div className="route-list-container">
      <h1 className='reo'>Route List</h1>
      <table className="route-table">
        <thead>
          <tr>
            <th>Departure City</th>
            <th>Arrival City</th>
            <th>Fare</th>
            <th>Duration</th>
            <th>Distance</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {routes.map((route) => (
            <tr key={route.id}>
              <td>{route.departureCity}</td>
              <td>{route.arrivalCity}</td>
              <td>{route.fare}</td>
              <td>{route.duration}</td>
              <td>{route.distance}</td>
              <td>
                <button onClick={() => onEdit(route)} className='routeedit'>
                  Edit
                </button>
                {/* <button onClick={() => onDelete(route.id)}>Delete</button> */}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default RouteList;