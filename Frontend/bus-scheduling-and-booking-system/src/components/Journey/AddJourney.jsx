import React, { useEffect, useState } from 'react';
import { Modal, Button, message, AutoComplete } from 'antd';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import BusService from '../../services/Bus.service';
import JourneyService from '../../services/Journey.service';
import RouteService from '../../services/Route.service';
import '../../css/AddJourney.css';

function AddJourney({ onAddJourney, onCancel }) {
    const [buses, setBuses] = useState([]); // State to store the list of buses
    const [routes, setRoutes] = useState([]); // State to store the list of routes

    // Fetch the list of buses and routes when the component mounts
    useEffect(() => {
        // Fetch buses and routes here using BusService and RouteService
        const fetchBusesAndRoutes = async () => {
            try {
                const busResponse = await BusService.getAllBuses();
                const routeResponse = await RouteService.getAllRoutes();
                setBuses(busResponse.data);
                setRoutes(routeResponse.data);
            } catch (error) {
                console.error('Error fetching data:', error);
                // Handle error
            }
        };

        fetchBusesAndRoutes();
    }, []);

    const initialValues = {
        busId: '', // Store the selectedOption here
        routeId: '',
        journeyDate: '',
        journeyTime: '',
        fare: '', // New field for fare
    };


    const validationSchema = Yup.object().shape({
        busId: Yup.string().required('Bus or Route is required'),
        routeId: Yup.string().required('Bus or Route is required'),
        journeyDate: Yup.date()
            .required('Date is required')
            .test('is-not-past', 'Date cannot be a past date', function (value) {
                const currentDate = new Date();
                const selectedDate = new Date(value);

                return selectedDate >= currentDate;
            }),
        journeyTime: Yup.string().required('Time is required'),
        fare: Yup.number().required('Fare is required').positive('Fare must be a positive number'),
    });



    const formik = useFormik({
        initialValues,
        validationSchema,
        onSubmit: async (values) => {
            try {
                const selectedRoute = routes.find((route) => route.routeId == values.routeId);

                // Calculate arrivalTime and arrivalDate
                const journeyTimeInMinutes = parseInt(values.journeyTime.split(':')[0]) * 60 + parseInt(values.journeyTime.split(':')[1]);
                const totalMinutes = journeyTimeInMinutes + selectedRoute.duration * 60; // Convert route duration to minutes

                const hours = Math.floor(totalMinutes / 60);
                const minutes = totalMinutes % 60;

                const arrivalTime = `${String(hours).padStart(2, '0')}:${String(minutes).padStart(2, '0')}`;

                // Calculate arrivalDate based on journeyDate and hours
                const journeyDate = new Date(values.journeyDate);
                journeyDate.setHours(hours);
                journeyDate.setMinutes(minutes);

                Modal.confirm({
                    title: 'Confirm Data Entry',
                    content: (
                        <div>
                            <p>Bus {getSelectedBusInfo(values.busId)}</p>
                            <p>Route {getSelectedRouteInfo(values.routeId)}</p>
                            <p>Journey Date: {values.journeyDate}</p>
                            <p>Journey Time: {values.journeyTime}</p>
                            <p>Arrival Date: {journeyDate.toDateString()}</p>
                            <p>Arrival Time: {arrivalTime}</p>
                            <p>Fare: ₹{values.fare}</p>
                        </div>
                    ),
                    onOk: async () => sendDataToServer(values, arrivalTime, journeyDate),
                });

            } catch (error) {
                console.error('Error:', error);
                message.error('An error occurred: ' + error.message);
            }
        },
    });

    const getSelectedBusInfo = (busId) => {
        const selectedBus = buses.find((bus) => bus.busId == busId);
        if (selectedBus) {
            return `ID: ${selectedBus.busId} ${selectedBus.busName} - ${selectedBus.busRegistrationNumber}`;
        } else {
            return 'No data';
        }
    };

    const getSelectedRouteInfo = (routeId) => {
        const selectedRoute = routes.find((route) => route.routeId == routeId);
        if (selectedRoute) {
            return `ID : ${selectedRoute.routeId}  ${selectedRoute.departureCity} - ${selectedRoute.arrivalCity}`;
        } else {
            return 'No data';
        }
    };

    const sendDataToServer = async (values, arrivalTime, arrivalDate) => {
        try {
            // // Format arrivalDate as "dd-mm-yyyy"
            // const formattedArrivalDate = `${arrivalDate.getDate().toString().padStart(2, '0')}-${
            //     (arrivalDate.getMonth() + 1).toString().padStart(2, '0')
            // }-${arrivalDate.getFullYear()}`;

            // Format arrivalTime as "HH:mm"
            const formattedArrivalTime = `${arrivalDate.getHours().toString().padStart(2, '0')}:${arrivalDate.getMinutes().toString().padStart(2, '0')}`;

            const updatedJourney = {
                ...values,
                arrivalTime: formattedArrivalTime,
                arrivalDate: arrivalDate,
            };

            console.log(updatedJourney)

            const response = await JourneyService.addJourney(
                updatedJourney,
                updatedJourney.busId,
                updatedJourney.routeId
            );

            if (response.status === 201) {
                message.success('Journey added successfully!');
                formik.resetForm();
                onCancel();
            } else {
                message.error('Failed to create Journey');
            }
        } catch (error) {
            console.error('Error creating Journey:', error);
            message.error('An error occurred while creating the bus: ' + error.message);
        }
    };

    return (
        <Modal
            open={true}
            title="Add Journey"
            onCancel={onCancel}
            footer={[
                <Button key="cancel" className="btn btn-secondary" onClick={onCancel}>
                    Cancel
                </Button>,
                <Button
                    key="save"
                    className="btn btn-primary"
                    onClick={formik.handleSubmit}
                >
                    Save
                </Button>,
            ]}
        >
            <div className="form-container">
                <form onSubmit={formik.handleSubmit}>
                    {/* AutoComplete input for selecting Bus */}
                    <AutoComplete
                        name="busId"
                        value={formik.values.busId}
                        onChange={(value) => formik.setFieldValue('busId', value)}
                        placeholder="Search for Bus"
                    >
                        {buses.length > 0 ? (
                            buses.map((bus) => (
                                <AutoComplete.Option key={`${bus.busId}`} value={`${bus.busId}`}>
                                    {`ID: ${bus.busId} ${bus.busName} - ${bus.busRegistrationNumber}`}
                                </AutoComplete.Option>
                            ))
                        ) : (
                            <AutoComplete.Option disabled>No data</AutoComplete.Option>
                        )}
                    </AutoComplete>
                    {formik.touched.busId && formik.errors.busId ? (
                        <div className="error-message">{formik.errors.busId}</div>
                    ) : null}

                    {/* AutoComplete input for selecting Route */}
                    <AutoComplete
                        name="routeId"
                        value={formik.values.routeId}
                        onChange={(value) => formik.setFieldValue('routeId', value)}
                        placeholder="Search for Route"
                    >
                        {routes.length > 0 ? (
                            routes.map((route) => (
                                <AutoComplete.Option key={`${route.routeId}`} value={`${route.routeId}`}>
                                    {`ID : ${route.routeId}  ${route.departureCity} - ${route.arrivalCity}`}
                                </AutoComplete.Option>
                            ))
                        ) : (
                            <AutoComplete.Option disabled>No data</AutoComplete.Option>
                        )}
                    </AutoComplete>
                    {formik.touched.routeId && formik.errors.routeId ? (
                        <div className="error-message">{formik.errors.routeId}</div>
                    ) : null}

                    <input
                        type="date"
                        id="journeyDate"
                        name="journeyDate"
                        placeholder="Journey Date"
                        value={formik.values.journeyDate}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                    />
                    {formik.touched.journeyDate && formik.errors.journeyDate ? (
                        <div className="error-message">{formik.errors.journeyDate}</div>
                    ) : null}

                    <input
                        type="time"
                        id="journeyTime"
                        name="journeyTime"
                        placeholder="Journey Time"
                        value={formik.values.journeyTime}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                    />
                    {formik.touched.journeyTime && formik.errors.journeyTime ? (
                        <div className="error-message">{formik.errors.journeyTime}</div>
                    ) : null}
                    <input
                        type="number"
                        id="fare"
                        name="fare"
                        placeholder="Fare"
                        value={formik.values.fare}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                    />
                    {formik.touched.fare && formik.errors.fare ? (
                        <div className="error-message">{formik.errors.fare}</div>
                    ) : null}
                </form>
            </div>
        </Modal>
    );
}

export default AddJourney;
