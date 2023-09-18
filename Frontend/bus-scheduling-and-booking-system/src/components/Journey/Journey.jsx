import React, { useState } from 'react';

import '../../css/Journey.css'







function Journey() {

    const [busIdFilter, setBusIdFilter] = useState('');

    const [routeIdFilter, setRouteIdFilter] = useState('');

    const [journeys, setJourneys] = useState([

        {

            journeytime: '2023-09-04 09:00 AM',

            busid: 101,

            routeid: 201,

        },

        {

            journeytime: '2023-09-04 11:30 AM',

            busid: 102,

            routeid: 202,

        },

        {

            journeytime: '2023-09-04 02:15 PM',

            busid: 103,

            routeid: 203,

        },

    ]);

    const [showAddForm, setShowAddForm] = useState(false);

    const [showUpdateForm, setShowUpdateForm] = useState(false);

    const [selectedJourneyIndex, setSelectedJourneyIndex] = useState(null);



    const [newJourney, setNewJourney] = useState({

        busid: '',

        routeid: '',

        date: '',

        time: '',

    });



    const [updatedJourney, setUpdatedJourney] = useState({

        busid: '',

        routeid: '',

        date: '',

        time: '',

    });



    const handleBusIdChange = (event) => {

        const value = event.target.value;

        setBusIdFilter(value);

        filterJourneys(value, routeIdFilter);

    };



    const handleRouteIdChange = (event) => {

        const value = event.target.value;

        setRouteIdFilter(value);

        filterJourneys(busIdFilter, value);

    };



    const filterJourneys = (busId, routeId) => {

        const filtered = journeys.filter((journey) => {

            return (

                journey.busid.toString().includes(busId) &&

                journey.routeid.toString().includes(routeId)

            );

        });

        setJourneys(filtered);

    };



    const handleAddClick = () => {

        setShowAddForm(true);

    };



    const handleFormChange = (event) => {

        const { name, value } = event.target;

        setNewJourney((prevJourney) => ({

            ...prevJourney,

            [name]: value,

        }));

    };



    const handleFormSubmit = (event) => {

        event.preventDefault();



        // Check for empty fields

        if (!newJourney.busid || !newJourney.routeid || !newJourney.date || !newJourney.time) {

            window.alert('All fields are required.');

            return;

        }



        // Combine date and time to create the journeytime

        const journeytime = `${newJourney.date} ${newJourney.time}`;



        // Add the new journey to the list of journeys

        setJourneys((prevJourneys) => [

            ...prevJourneys,

            {

                ...newJourney,

                journeytime,

            },

        ]);



        // Reset the form

        setNewJourney({

            busid: '',

            routeid: '',

            date: '',

            time: '',

        });



        setShowAddForm(false);

    };



    const handleUpdateClick = (index) => {

        setSelectedJourneyIndex(index);

        setUpdatedJourney({

            busid: journeys[index].busid.toString(),

            routeid: journeys[index].routeid.toString(),

            date: '',

            time: '',

        });

        setShowUpdateForm(true);

    };



    const handleUpdateFormChange = (event) => {

        const { name, value } = event.target;

        setUpdatedJourney((prevJourney) => ({

            ...prevJourney,

            [name]: value,

        }));

    };



    const handleUpdateFormSubmit = (event) => {

        event.preventDefault();



        // Check for empty fields

        if (

            !updatedJourney.busid ||

            !updatedJourney.routeid ||

            !updatedJourney.date ||

            !updatedJourney.time

        ) {

            window.alert('All fields are required.');

            return;

        }



        // Combine date and time to create the journeytime

        const journeytime = `${updatedJourney.date} ${updatedJourney.time}`;



        // Update the selected journey in the list

        setJourneys((prevJourneys) =>

            prevJourneys.map((journey, index) =>

                index === selectedJourneyIndex

                    ? {

                        ...journey,

                        busid: updatedJourney.busid,

                        routeid: updatedJourney.routeid,

                        journeytime,

                    }

                    : journey

            )

        );



        // Reset the form and close the update form

        setUpdatedJourney({

            busid: '',

            routeid: '',

            date: '',

            time: '',

        });



        setShowUpdateForm(false);

    };



    return (

        <div className="journey-list">

            <div className="search-section">

                <div className="search-box">

                    <label htmlFor="busIdFilter">Filter by Bus ID:</label>

                    <input

                        type="text"

                        id="busIdFilter"

                        name="busIdFilter"

                        placeholder='Enter BusId'

                        value={busIdFilter}

                        onChange={handleBusIdChange}

                    />

                </div>

                <div className="search-box">

                    <label htmlFor="routeIdFilter">Filter by Route ID:</label>

                    <input

                        type="text"

                        id="routeIdFilter"

                        name="routeIdFilter"

                        placeholder='Enter RouteId'

                        value={routeIdFilter}

                        onChange={handleRouteIdChange}

                    />

                </div>

                <div className="add-button-container">

                    <button className="add-button" onClick={handleAddClick}>

                        Add

                    </button>

                </div>

            </div>

            <table className="journey-table">

                <thead>

                    <tr>

                        <th>Journey Time</th>

                        <th>Bus ID</th>

                        <th>Route ID</th>

                        <th>Actions</th>

                    </tr>

                </thead>

                <tbody>

                    {journeys.map((journey, index) => (

                        <tr key={index}>

                            <td>{journey.journeytime}</td>

                            <td>{journey.busid}</td>

                            <td>{journey.routeid}</td>

                            <td>

                                <button onClick={() => handleUpdateClick(index)} className='update123'>Update</button>

                            </td>

                        </tr>

                    ))}

                </tbody>

            </table>

            {showAddForm && (

                <div className="modal">

                    <div className="modal-content">

                        <form onSubmit={handleFormSubmit}>

                            <h2>Add Journey</h2>

                            <div className="form-group">

                                <label htmlFor="newBusId">Bus ID:</label>

                                <input

                                    type="text"

                                    id="newBusId"

                                    name="busid"

                                    value={newJourney.busid}

                                    onChange={handleFormChange}

                                />

                            </div>

                            <div className="form-group">

                                <label htmlFor="newRouteId">Route ID:</label>

                                <input

                                    type="text"

                                    id="newRouteId"

                                    name="routeid"

                                    value={newJourney.routeid}

                                    onChange={handleFormChange}

                                />

                            </div>

                            <div className="form-group">

                                <label htmlFor="newDate">Date:</label>

                                <input

                                    type="date"

                                    id="newDate"

                                    name="date"

                                    value={newJourney.date}

                                    onChange={handleFormChange}

                                />

                            </div>

                            <div className="form-group">

                                <label htmlFor="newTime">Time:</label>

                                <input

                                    type="time"

                                    id="newTime"

                                    name="time"

                                    value={newJourney.time}

                                    onChange={handleFormChange}

                                />

                            </div>

                            <div className="button-group">

                                <button type="submit">Add Journey</button>

                                <button onClick={() => setShowAddForm(false)}>Cancel</button>

                            </div>

                        </form>

                    </div>

                </div>

            )}

            {showUpdateForm && (

                <div className="modal">

                    <div className="modal-content">

                        <form onSubmit={handleUpdateFormSubmit}>

                            <h2>Update Journey</h2>

                            <div className="form-group ">

                                <label htmlFor="updateBusId">Bus ID:</label>

                                <input

                                    type="text"

                                    id="updateBusId"

                                    name="busid"

                                    value={updatedJourney.busid}

                                    onChange={handleUpdateFormChange}



                                />





                            </div>

                            <div className="form-group ">

                                <label htmlFor="updateRouteId">Route ID:</label>

                                <input

                                    type="text"

                                    id="updateRouteId"

                                    name="routeid"

                                    value={updatedJourney.routeid}

                                    onChange={handleUpdateFormChange}

                                />

                            </div>

                            <div className="form-group ">

                                <label htmlFor="updateDate">Date:</label>

                                <input

                                    type="date"

                                    id="updateDate"

                                    name="date"

                                    value={updatedJourney.date}

                                    onChange={handleUpdateFormChange}

                                />

                            </div>

                            <div className="form-group ">

                                <label htmlFor="updateTime">Time:</label>

                                <input

                                    type="time"

                                    id="updateTime"

                                    name="time"

                                    value={updatedJourney.time}

                                    onChange={handleUpdateFormChange}

                                />

                            </div>

                            <div className="button-group">

                                <button type="submit">Update Journey</button>

                                <button onClick={() => setShowUpdateForm(false)}>Cancel</button>

                            </div>

                        </form>

                    </div>

                </div>

            )}

        </div>

    );

}



export default Journey;