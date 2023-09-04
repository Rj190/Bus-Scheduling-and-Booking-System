// UserForm.js
import React from 'react';
import { Form, Button, Col, Row,Container } from 'react-bootstrap';
import UserService from '../services/User.service'
import { useFormik } from 'formik';
import * as yup from 'yup';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const validationSchema = yup.object({
  username: yup.string().required('Username is required'),
  password: yup.string().required('Password is required'),
  firstName: yup.string().required('First Name is required'),
  lastName: yup.string().required('Last Name is required'),
  email: yup.string().email('Invalid email').required('Email is required'),
  contactNumber: yup.string().required('Contact Number is required'),
  userRole: yup.string().required('User Role is required'),
});

const RegisterUserForm = () => {
  const formik = useFormik({
    initialValues: {
      username: '',
      password: '',
      firstName: '',
      lastName: '',
      email: '',
      contactNumber: '',
      userRole: '',
    },
    validationSchema: validationSchema,
    onSubmit: async (values, { resetForm }) => {
      try {
        const createdUser = await UserService.createUser(values);
        // Display success notification
        toast.success('User created successfully');
        resetForm();
      } catch (error) {
        // Display error notification
        toast.error('Error creating user');
        console.error('Error creating user:', error);
      }
    },
  });

  return (
        <Container>
          <h2 className="text-center mb-4">User Registration</h2>
          <Form>
            <Row>
              <Col md={6}>
                <Form.Group controlId="username">
                  <Form.Label>Username</Form.Label>
                  <Form.Control type="text" placeholder="Enter username" />
                </Form.Group>
              </Col>
              <Col md={6}>
                <Form.Group controlId="password">
                  <Form.Label>Password</Form.Label>
                  <Form.Control type="password" placeholder="Enter password" />
                </Form.Group>
              </Col>
            </Row>
            <Form.Group controlId="firstName">
              <Form.Label>First Name</Form.Label>
              <Form.Control type="text" placeholder="Enter first name" />
            </Form.Group>
            <Form.Group controlId="lastName">
              <Form.Label>Last Name</Form.Label>
              <Form.Control type="text" placeholder="Enter last name" />
            </Form.Group>
            <Form.Group controlId="email">
              <Form.Label>Email Address</Form.Label>
              <Form.Control type="email" placeholder="Enter email" />
            </Form.Group>
            <Form.Group controlId="contactNumber">
              <Form.Label>Contact Number</Form.Label>
              <Form.Control type="tel" placeholder="Enter contact number" />
            </Form.Group>
            <Form.Group controlId="userRole">
              <Form.Label>User Role</Form.Label>
              <Form.Control as="select">
                <option>Admin</option>
                <option>User</option>
              </Form.Control>
            </Form.Group>
            <Button variant="primary" type="submit">
              Register
            </Button>
          </Form>
        </Container>
      );
};

export default RegisterUserForm;
