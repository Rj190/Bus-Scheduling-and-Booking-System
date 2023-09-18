import React, { useState,useEffect } from 'react';
import {  Button } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import AddBus from './AddBus'; // Import your AddBus component here
import EditBus from './EditBus'; // Import your EditBus component hereimport BusList from './BusList'; // Import your BusList component here
import '../../css/Bus.css'
import BusService from '../../services/Bus.service';
import BusList from './BusList'

function BusManagement() {
  const [buses, setBuses] = useState([]);
  const [editingBus, setEditingBus] = useState(null);
  const [showAddModal, setShowAddModal] = useState(false);

  const fetchBuses = async () => {
    try {
      const allBuses = await BusService.getAllBuses();
      setBuses(allBuses.data)
    } catch (error) {
      console.error('Error fetching routes:', error);
    }
  };

  useEffect(() => {
    fetchBuses();
  }, []); // Empty dependency array ensures this effect runs only once when the component mounts



  const handleAddBus = (newBus) => {
    setBuses([...buses, newBus]);
    setShowAddModal(false);
  };

  const handleEditBus = (editedBus) => {
    fetchBuses();
    setEditingBus(null);
  };


  return (
    <div className='side-container'>
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
      <BusList buses={buses} onEdit={(bus) => setEditingBus(bus)} />
      {showAddModal && (
        <AddBus
          onAddBus={handleAddBus}
          onCancel={() => setShowAddModal(false)}
        />
      )}
    </div>
  );
}

export default BusManagement;