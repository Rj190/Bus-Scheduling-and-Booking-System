import React from 'react';
import '../../css/Bus.css'


function BusList({ buses, onEdit }) {
  return (
    <div className="bus-list-container">
    <h1 className="reo">Bus List</h1>
    <table className="bus-table">
      <thead>
        <tr>
          <th>Bus Name</th>
          <th>Bus Type</th>
          <th>Bus Capacity</th>
          <th>Bus RegistrationNumber</th>
          <th>Bus Availability Status</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        {buses.map((bus) => (
          <tr key={bus.busId}>
            <td>{bus.busName}</td>
            <td>{bus.busType}</td>
            <td>{bus.busCapacity}</td>
            <td>{bus.busRegistrationNumber}</td>
            <td>{bus.busAvailabilityStatus}</td>
            <td>
              <button onClick={() => onEdit(bus)} className="buseedit">
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

export default BusList;