import React, { useState } from 'react';
import { Modal, Input, Button, message } from 'antd';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import axios from 'axios';
import BusService from '../../services/Bus.service';

function AddBus({ onAddBus, onCancel }) {

    const initialValues = {
        busId: 0,
        busName: '',
        busType: '',
        busCapacity: '',
        busRegistrationNumber: '',
        busAvailabilityStatus: '',
    };

    const validationSchema = Yup.object().shape({
        busName: Yup.string()
            .required('Bus name is required')
            .min(2, 'Bus name must be at least 2 characters')
            .max(20, 'Bus name must not exceed 20 characters'),
        busType: Yup.string().required('Bus type is required'),
        busCapacity: Yup.number().min(20, 'Bus capacity must be at least 20'),
        busRegistrationNumber: Yup.string()
            .required('Bus registration number is required')
            .max(20, 'Bus registration number must not exceed 20 characters'),
        busAvailabilityStatus: Yup.string().matches(/^(Available|Not Available)$/, 'Invalid availability status'),
    });

    const formik = useFormik({
        initialValues,
        validationSchema,
        onSubmit: async (values) => {
            try {
                Modal.confirm({
                    title: 'Confirm Data Entry',
                    content: (
                        <div>
                              <p>Bus Name: {values.busName}</p>
                              <p>Bus Type: {values.busType}</p>
                              <p>Bus Capacity: {values.busCapacity}</p>
                              <p>Bus RegistrationNumber: {values.busRegistrationNumber}</p>
                              <p>Bus Availability Status: {values.busAvailabilityStatus}</p>
                        </div>
                    ),
                    onOk: async () => sendDataToServer(values),
                });
            } catch (error) {
                console.error('Error:', error);
                message.error('An error occurred: ' + error.message);
            }
        },
    });

    const sendDataToServer = async (values) => {
        try {
            const updatedBus = { ...values };
            generateSeats(updatedBus);
            const response = await BusService.createBus(updatedBus);
            onAddBus(response.data);
            if (response.status === 201) {
                message.success('Bus added successfully!');
                formik.resetForm();
            } else {
                message.error('Failed to create bus');
            }
        } catch (error) {
            console.error('Error creating bus:', error);
            message.error('An error occurred while creating the bus: ' + error.message);
        }
    };

    const generateSeats = (busToUpdate) => {
        const newSeats = [];
        for (let row = 1; row <= busToUpdate.busCapacity; row++) {
            const seatNumber = 'S' + row;
            const seat = {
                seatNumber: seatNumber,
                seatAvailabilityStatus: 'Available',
            };
            newSeats.push(seat);
        }
        busToUpdate.seats = newSeats;
    };

    return (
        <Modal
            open={true}
            title="Add Bus"
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
                    <input
                        type="text"
                        name="busName"
                        className="form-input"
                        placeholder="Bus Name"
                        value={formik.values.busName}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                    />
                    {formik.touched.busName && formik.errors.busName ? (
                        <div className="error-message">{formik.errors.busName}</div>
                    ) : null}

                    <input
                        type="text"
                        name="busType"
                        className="form-input"
                        placeholder="Bus Type"
                        value={formik.values.busType}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                    />
                    {formik.touched.busType && formik.errors.busType ? (
                        <div className="error-message">{formik.errors.busType}</div>
                    ) : null}

                    <input
                        type="number"
                        name="busCapacity"
                        className="form-input"
                        placeholder="Capacity"
                        value={formik.values.busCapacity}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                    />
                    {formik.touched.busCapacity && formik.errors.busCapacity ? (
                        <div className="error-message">{formik.errors.busCapacity}</div>
                    ) : null}

                    <input
                        type="text"
                        name="busRegistrationNumber"
                        className="form-input"
                        placeholder="Registration Number"
                        value={formik.values.busRegistrationNumber}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                    />
                    {formik.touched.busRegistrationNumber && formik.errors.busRegistrationNumber ? (
                        <div className="error-message">{formik.errors.busRegistrationNumber}</div>
                    ) : null}

                    <input
                        type="text"
                        name="busAvailabilityStatus"
                        className="form-input"
                        placeholder="Availability Status"
                        value={formik.values.busAvailabilityStatus}
                        onChange={formik.handleChange}
                        onBlur={formik.handleBlur}
                    />
                    {formik.touched.busAvailabilityStatus && formik.errors.busAvailabilityStatus ? (
                        <div className="error-message">{formik.errors.busAvailabilityStatus}</div>
                    ) : null}
                </form>
            </div>
        </Modal>
    );
}

export default AddBus;
