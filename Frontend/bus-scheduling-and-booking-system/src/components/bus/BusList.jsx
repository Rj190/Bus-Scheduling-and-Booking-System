import React from 'react';

import { useState } from 'react';

 

function BusList({ buses, onEdit, onDelete }) {

 

  // Check if 'buses' is an array

 

  if (!Array.isArray(buses)) {

 

    return null; // Return null or handle the error as needed

 

  }

  return (

 

<div className="bus-list">

 

<h1>Bus List</h1>

 

<ul>

 

        {buses.map((bus) => (

 

<li key={bus.BusID}>

 

            {bus.BusType} (ID: {bus.BusID})

 

<div className="list-buttons">

 

<button onClick={() => onEdit(bus)}>Edit</button>

 

<button onClick={() => onDelete(bus.BusID)}>Delete</button>

 

</div>

 

</li>

 

        ))}

 

</ul>

 

</div>

 

  );

 

}

export default BusList;