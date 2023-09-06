import React, { useState } from 'react';

 

import { Modal, Button } from 'react-bootstrap';

 

import 'bootstrap/dist/css/bootstrap.min.css';

 

// Import your EditBus component here

function EditBus({ bus, onUpdateBus, onCancel }) {

 

  const [editedBus, setEditedBus] = useState(bus);

 

  const [validationError, setValidationError] = useState('');

  const handleInputChange = (event) => {

 

    const { name, value } = event.target;

 

    setEditedBus({

 

      ...editedBus,

 

      [name]: value,

 

    });

 

  };

  const handleUpdateBus = () => {

 

    // Validate input fields

 

    if (!editedBus.BusType || !editedBus.Capacity || !editedBus.RegistrationNumber || !editedBus.AvailabilityStatus) {

 

      setValidationError('All fields are required.');

 

      return;

 

    }

    // Update the selectedBus in the 'buses' state or send a request to your server to update the bus.

 

    onUpdateBus(editedBus);

    // Clear the editedBus state after updating.

 

    setEditedBus(null);

 

  };

  return (

 

<Modal show={true} onHide={onCancel}>

 

<Modal.Header closeButton>

 

<Modal.Title>Edit Bus</Modal.Title>

 

</Modal.Header>

 

<Modal.Body>

 

<div className="form-container">

 

<form>

 

<div className="form-group">

 

<input

 

                type="text"

 

                name="BusType"

 

                placeholder="Bus Type"

 

                value={editedBus.BusType}

 

                onChange={handleInputChange}

 

                className="form-input"

 

              />

 

</div>

 

<div className="form-group">

 

<input

 

                type="number"

 

                name="Capacity"

 

                placeholder="Capacity"

 

                value={editedBus.Capacity}

 

                onChange={handleInputChange}

 

                className="form-input"

 

              />

 

</div>

 

<div className="form-group">

 

<input

 

                type="text"

 

                name="RegistrationNumber"

 

                placeholder="Registration Number"

 

                value={editedBus.RegistrationNumber}

 

                onChange={handleInputChange}

 

                className="form-input"

 

              />

 

</div>

 

<div className="form-group">

 

<input

 

                type="text"

 

                name="AvailabilityStatus"

 

                placeholder="Availability Status"

 

                value={editedBus.AvailabilityStatus}

 

                onChange={handleInputChange}

 

                className="form-input"

 

              />

 

</div>

 

            {validationError && <p className="validation-error">{validationError}</p>}

 

</form>

 

</div>

 

</Modal.Body>

 

<Modal.Footer>

 

<Button variant="secondary" onClick={onCancel}>

 

          Cancel

 

</Button>

 

<Button variant="primary" onClick={handleUpdateBus}>

 

          Save

 

</Button>

 

</Modal.Footer>

 

</Modal>

 

  );

 

}

export default EditBus;