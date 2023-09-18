import React, { useState, useEffect } from 'react';
import { Button } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import AddJourney from './AddJourney';
import EditJourney from './EditJourney';
import JourneyList from './JourneyList';
import JourneyService from '../../services/Journey.service';
import '../../css/Journey.css'

function JourneyManagement() {
  const [journeys, setJourneys] = useState([]);
  const [editingJourney, setEditingJourney] = useState(null);
  const [showAddModal, setShowAddModal] = useState(false);

  const fetchJourneys = async () => {
    try {
      const allJourneys = await JourneyService.getAllJourneys();
      setJourneys(allJourneys);
      console.log(journeys)
    } catch (error) {
      console.error('Error fetching journeys:', error);
    }
  };

  useEffect(() => {
    fetchJourneys();
  }, []);

  const handleAddJourney = (newJourney) => {
    setJourneys([...journeys, newJourney]);
    setShowAddModal(false);
  };

  const handleEditJourney = (editedJourney) => {
    fetchJourneys();
    setEditingJourney(null);
  };

  return (
    <div>
      <h1>Journey Management System</h1>
      <Button onClick={() => setShowAddModal(true)} className="add-journey-button">
        Add Journey
      </Button>
      {editingJourney ? (
        <EditJourney
          journey={editingJourney}
          onUpdateJourney={handleEditJourney}
          onCancel={() => setEditingJourney(null)}
        />
      ) : null}
      <JourneyList journeys={journeys} onEdit={(journey) => setEditingJourney(journey)} />
      {showAddModal && (
        <AddJourney
          onAddJourney={handleAddJourney}
          onCancel={() => setShowAddModal(false)}
        />
      )}
    </div>
  );
}

export default JourneyManagement;
