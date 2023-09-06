import React, { useState } from 'react';

 

import { Modal, Button } from 'react-bootstrap';

 

import 'bootstrap/dist/css/bootstrap.min.css';

 

import AddBus from './AddBus'; // Import your AddBus component here

 

import EditBus from './EditBus'; // Import your EditBus component here

 

import BusList from './BusList'; // Import your BusList component here

 

import '../../css/Bus.css'

function BusManagement() {

 

  const [buses, setBuses] = useState([]);

 

  const [editingBus, setEditingBus] = useState(null);

 

  const [showAddModal, setShowAddModal] = useState(false);

  const handleAddBus = (newBus) => {

 

    setBuses([...buses, newBus]);

 

    setShowAddModal(false);

 

  };

 

  const handleEditBus = (editedBus) => {

 

    const updatedBuses = buses.map((bus) =>

 

      bus.BusID === editedBus.BusID ? editedBus : bus

 

    );

 

    setBuses(updatedBuses);

 

    setEditingBus(null);

 

  };

  const handleDeleteBus = (busId) => {

 

    const updatedBuses = buses.filter((bus) => bus.BusID !== busId);

 

    setBuses(updatedBuses);

 

    setEditingBus(null);

 

  };

  return (

 

<div>

 

<h1>Bus Management System</h1>

 

<Button onClick={() => setShowAddModal(true)} className="add-route-button">

 

        Add Bus

 

</Button>

 

      {editingBus ? (

 

<EditBus

 

          bus={editingBus}

 

          onUpdateBus={handleEditBus}

 

          onCancel={() => setEditingBus(null)}

 

        />

 

      ) : null}

 

<BusList buses={buses} onEdit={(bus) => setEditingBus(bus)} onDelete={handleDeleteBus} />

 

<Modal show={showAddModal} onHide={() => setShowAddModal(false)}>

 

<Modal.Header closeButton>

 

<Modal.Title>Add Bus</Modal.Title>

 

</Modal.Header>

 

<Modal.Body>

 

<AddBus onAddBus={handleAddBus} onCloseModal={() => setShowAddModal(false)} />

 

</Modal.Body>

 

<Modal.Footer>

 

<Button variant="secondary" onClick={() => setShowAddModal(false)}>

 

            Close

 

</Button>

 

</Modal.Footer>

 

</Modal>

 

</div>

 

  );

 

}

export default BusManagement;