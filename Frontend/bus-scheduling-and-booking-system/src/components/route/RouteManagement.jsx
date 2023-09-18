import React, { useState, useEffect } from 'react';
import { Button } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import AddRoute from './AddRoute';
import EditRoute from './EditRoute';
import RouteList from './RouteList';
import '../../css/route.css';
import RouteService from '../../services/Route.service';

function RouteManagement() {
  const [routes, setRoutes] = useState([]);
  const [editingRoute, setEditingRoute] = useState(null);
  const [showAddRouteModal, setShowAddRouteModal] = useState(false);

  const fetchRoutes = async () => {
    try {
      const allRoutes = await RouteService.getAllRoutes();
      setRoutes(allRoutes.data);
    } catch (error) {
      console.error('Error fetching routes:', error);
    }
  };

  useEffect(() => {
    fetchRoutes();
  }, []); // Empty dependency array ensures this effect runs only once when the component mounts

  const handleEditRoute = () => {
    fetchRoutes()
    setEditingRoute(null);
  };

  const handleAddRoute = (newRoute) => {
    setRoutes([...routes, newRoute]);
    setShowAddRouteModal(false);
  };

  return (
    <div>
      <h1 className='rei'>Route Management System</h1>
      <Button className="add-route-button" onClick={() => setShowAddRouteModal(true)}>
        Add Route
      </Button>
      {editingRoute ? (
        <EditRoute
          route={editingRoute}
          onUpdateRoute={handleEditRoute}
          onCancel={() => setEditingRoute(null)}
        />
      ) : null}
      <RouteList routes={routes} onEdit={(route) => setEditingRoute(route)} />
      {showAddRouteModal && (
        <AddRoute
          onAddRoute={handleAddRoute}
          onCancel={() => setShowAddRouteModal(false)}
        />
      )}
    </div>
  );
}

export default RouteManagement;
