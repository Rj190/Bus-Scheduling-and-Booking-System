import React, { useState } from 'react';
import { Modal, Input, InputNumber, message, Button } from 'antd';
import * as Yup from 'yup'; // Import Yup for validation
import { useFormik } from 'formik'; // Import useFormik for form handling
import RouteService from '../../services/Route.service' // Import the route service

function EditRoute({ route, onUpdateRoute, onCancel }) {
  const validationSchema = Yup.object().shape({
    departureCity: Yup.string()
      .required('Departure city is required')
      .min(2, 'Departure city must be at least 2 characters')
      .max(100, 'Departure city must be at most 100 characters'),
    arrivalCity: Yup.string()
      .required('Arrival city is required')
      .min(2, 'Arrival city must be at least 2 characters')
      .max(100, 'Arrival city must be at most 100 characters'),
    distance: Yup.number()
      .required('Distance is required')
      .min(1, 'Distance must be at least 1'),
    duration: Yup.string().required('Duration is required'),
  });

  const formik = useFormik({
    initialValues: {
      departureCity: route.departureCity,
      arrivalCity: route.arrivalCity,
      distance: route.distance,
      duration: route.duration,
    },
    validationSchema,
    onSubmit: async (values) => {
      Modal.confirm({
        title: 'Confirm Data Entry',
        content: (
          <div>
            <p>Departure City: {values.departureCity}</p>
            <p>Arrival City: {values.arrivalCity}</p>
            <p>Distance: {values.distance} km</p>
            <p>Duration: {values.duration} hours</p>
          </div>
        ),
        onOk: async () => {
          try {

            const response = await RouteService.updateRoute(route.routeId, values);
            if (response.status === 200) {
              message.success('Route updated successfully');
              onUpdateRoute(values); // Update the route in the parent component
              onCancel(); // Close the modal
            } else {
              message.error('Failed to update route');
            }
          } catch (error) {
            console.error('Error updating route:', error);
            message.error('Failed to update route');
          }
        },
      });
    },
  });

  return (
    <Modal
      open={true}
      title="Edit Route"
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
        </Button>
      ]}
    >
      <div className="form-container">
        <form onSubmit={formik.handleSubmit}>
          <div className="form-group">
            <Input
              type="text"
              name="departureCity"
              placeholder="Departure City"
              value={formik.values.departureCity}
              onChange={formik.handleChange}
            />
            {formik.touched.departureCity && formik.errors.departureCity ? (
              <div className="validation-error">
                {formik.errors.departureCity}
              </div>
            ) : null}
          </div>
          <div className="form-group">
            <Input
              type="text"
              name="arrivalCity"
              placeholder="Arrival City"
              value={formik.values.arrivalCity}
              onChange={formik.handleChange}
            />

            {formik.touched.arrivalCity && formik.errors.arrivalCity ? (
              <div className="validation-error">
                {formik.errors.arrivalCity}
              </div>
            ) : null}
          </div>
          <div className="form-group">
            <InputNumber
              name="distance"
              placeholder="Distance (in km)"
              value={formik.values.distance}
              onChange={(value) => formik.setFieldValue('distance', value)}
            />
            {formik.touched.distance && formik.errors.distance ? (
              <div className="validation-error">{formik.errors.distance}</div>
            ) : null}
          </div>
          <div className="form-group">
            <InputNumber
              name="duration"
              placeholder="Duration (in hours)"
              value={formik.values.duration}
              onChange={(value) => formik.setFieldValue('duration', value)}
            />
            {formik.touched.duration && formik.errors.duration ? (
              <div className="validation-error">{formik.errors.duration}</div>
            ) : null}
          </div>
        </form>
      </div>
    </Modal>
  );
}

export default EditRoute;
