
import React, { useState } from 'react';
import { Modal, Button } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../../css/route.css'

function EditRoute({ route, onUpdateRoute, onCancel }) {
    const [editedRoute, setEditedRoute] = useState(route);
    const [validationError, setValidationError] = useState('');

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setEditedRoute({
            ...editedRoute,
            [name]: value,
        });
    };

    const handleUpdateRoute = () => {
        // Validate input fields
        if (
            !editedRoute.arrivalCity ||
            !editedRoute.departureCity ||
            !editedRoute.fare ||
            !editedRoute.distance ||
            !editedRoute.duration
        ) {
            setValidationError('All fields are required.');
            return;
        }

        // Update the selectedRoute in the 'routes' state or send a request to your server to update the route.
        onUpdateRoute(editedRoute);

        // Clear the editedRoute state after updating.
        setEditedRoute(null);
    };

    return (
        <Modal show={true} onHide={onCancel}>
            <Modal.Header closeButton>
                <Modal.Title className='routebutton'>Edit Route</Modal.Title>
            </Modal.Header>
            <Modal.Body >
                <div className="form-container">
                    <form>
                        <div className="form-group">
                            <input
                                type="text"
                                name="departureCity"
                                placeholder="Departure City"
                                value={editedRoute.departureCity}
                                onChange={handleInputChange}
                                className="form-input"
                            />
                        </div>
                        <div className="form-group">
                            <input
                                type="text"
                                name="arrivalCity"
                                placeholder="Arrival City"
                                value={editedRoute.arrivalCity}
                                onChange={handleInputChange}
                                className="form-input"
                            />
                        </div>
                        <div className="form-group">
                            <input
                                type="number"
                                name="fare"
                                placeholder="Fare"
                                value={editedRoute.fare}
                                onChange={handleInputChange}
                                className="form-input"
                            />
                        </div>
                        <div className="form-group">
                            <input
                                type="number"
                                name="distance"
                                placeholder="Distance"
                                value={editedRoute.distance}
                                onChange={handleInputChange}
                                className="form-input"
                            />
                        </div>
                        <div className="form-group">
                            <input
                                type="text"
                                name="duration"
                                placeholder="Duration"
                                value={editedRoute.duration}
                                onChange={handleInputChange}
                                className="form-input"
                            />
                        </div>
                        {validationError && (
                            <p className="validation-error">{validationError}</p>
                        )}
                    </form>
                </div>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={onCancel}>
                    Cancel
                </Button>
                <Button variant="primary" onClick={handleUpdateRoute}>
                    Save
                </Button>
            </Modal.Footer>
        </Modal>
    );
}

export default EditRoute;