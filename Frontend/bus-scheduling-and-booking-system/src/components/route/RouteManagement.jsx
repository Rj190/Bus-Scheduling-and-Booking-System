import React, { useState } from 'react';
import { Modal, Button } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import AddRoute from './AddRoute'; // Import your AddRoute component here
import EditRoute from './EditRoute'; // Import your EditRoute component here
import RouteList from './RouteList'; // Import your RouteList component here
import '../../css/route.css'

function RouteManagement() {
  const [routes, setRoutes] = useState([]);
  const [editingRoute, setEditingRoute] = useState(null);
  
  const [showAddModal, setShowAddModal] = useState(false);

  const handleAddRoute = (newRoute) => {
    setRoutes([...routes, newRoute]);
    setShowAddModal(false);
  };

  const handleEditRoute = (editedRoute) => {
    const updatedRoutes = routes.map((route) =>
      route.RouteID === editedRoute.RouteID ? editedRoute : route
    );
    setRoutes(updatedRoutes);
    
    setEditingRoute(null);
  };

  const handleDeleteRoute = (routeId) => {
    const updatedRoutes = routes.filter((route) => route.RouteID !== routeId);
    setRoutes(updatedRoutes);
    setEditingRoute(null);
  };

  return (
    <div>
      <h1 className='rei'>Route Management System</h1>
      <Button onClick={() => setShowAddModal(true)} className="add-route-button">
        Add Route
      </Button>
      {editingRoute ? (
        <EditRoute
          route={editingRoute}
          onUpdateRoute={handleEditRoute}
          onCancel={() => setEditingRoute(null)}
        />
      ) : null}
      <RouteList routes={routes} onEdit={(route) => setEditingRoute(route)} onDelete={handleDeleteRoute} />
      <Modal show={showAddModal} onHide={() => setShowAddModal(false)}>
        <Modal.Header closeButton>
          <Modal.Title >Add Route</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <AddRoute onAddRoute={handleAddRoute} onCloseModal={() => setShowAddModal(false)} />
        </Modal.Body>
        <Modal.Footer>
          <Button variant="primary" onClick={() => setShowAddModal(false)}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
}

export default RouteManagement;