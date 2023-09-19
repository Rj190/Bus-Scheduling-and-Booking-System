import React, { useState } from 'react';
import { Form, Button, Container, Row, Col } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
    faUserPlus,
    faUser,
    faLock,
    faEnvelope,
    faPhone,
    faUserTag,
} from '@fortawesome/free-solid-svg-icons';
import { Formik, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import UserService from '../services/User.service';

import Modal from 'antd/es/modal/index';

import '../css/RegistrationForm.css'; // Import your CSS file here
import { useNavigate,Link } from 'react-router-dom';
import WalletService from '../services/Wallet.Service';

const RegistrationForm = () => {

    const navigate = useNavigate();

    const initialValues = {
        firstName: '',
        lastName: '',
        username: '',
        password: '',
        confirmPassword: '',
        email: '',
        contactNumber: '',
        userRole: 'User', // Default role
    };

    const validationSchema = Yup.object().shape({
        firstName: Yup.string().required('First Name is required'),
        lastName: Yup.string().required('Last Name is required'),
        username: Yup.string().required('Username is required'),
        password: Yup.string().required('Password is required'),
        confirmPassword: Yup.string()
            .oneOf([Yup.ref('password'), null], 'Passwords must match')
            .required('Confirm Password is required'),
        email: Yup.string().email('Invalid email format').required('Email is required'),
        contactNumber: Yup.string()
            .matches(/^[0-9]+$/, 'Must be only digits')
            .min(10, 'Must be exactly 10 digits')
            .max(10, 'Must be exactly 10 digits')
            .required('Contact Number is required'),
    });

    const handleSubmit = async (values, { setSubmitting }) => {
        try {
            const response = await UserService.createUser(values);
            console.log('User created:', response.data);

            if (response.status === 201) {
                setSubmitting(false);
                const wallet = await WalletService.createWallet(response.data.username)
                Modal.info({
                    title: "You are Successfully registered",
                    content: (
                        <p><strong>User-Name : {response.data.username}</strong></p>
                    )
                });
                navigate("/login")
            } else {
                Modal.error({
                    title: "Error",
                    content: (
                        <p>Failed to register. Please try again.</p>
                    )
                });

            }
        } catch (error) {
            Modal.error({
                title: "Error",
                content: (
                    <p>{error.response?.data || 'Error creating user.'}</p>
                )
            });
        }
    };


    return (
        <div className="registration-container">
            <div className="text-center">
                <FontAwesomeIcon icon={faUserPlus} />
                <h3 className="mt-2">User Registration</h3>
            </div>
            <Formik
                initialValues={initialValues}
                validationSchema={validationSchema}
                onSubmit={handleSubmit}
            >
                {({ handleSubmit, isSubmitting }) => (
                    <Form onSubmit={handleSubmit}>
                        {/* First Name and Last Name */}
                        <Row>
                            <Col md={6}>
                                <Form.Group controlId="firstName">
                                    <Form.Label>
                                        First Name <span className="required">*</span>
                                    </Form.Label>
                                    <Field
                                        type="text"
                                        name="firstName"
                                        as={Form.Control}
                                        placeholder="Enter first name"
                                    />
                                    <ErrorMessage name="firstName" component="div" className="error" />
                                </Form.Group>
                            </Col>
                            <Col md={6}>
                                <Form.Group controlId="lastName">
                                    <Form.Label>
                                        Last Name <span className="required">*</span>
                                    </Form.Label>
                                    <Field
                                        type="text"
                                        name="lastName"
                                        as={Form.Control}
                                        placeholder="Enter last name"
                                    />
                                    <ErrorMessage name="lastName" component="div" className="error" />
                                </Form.Group>
                            </Col>
                        </Row>

                        <Form.Group controlId="username">
                            <Form.Label>
                                <FontAwesomeIcon icon={faUser} /> Username <span className="required">*</span>
                            </Form.Label>
                            <Field
                                type="text"
                                name="username"
                                as={Form.Control}
                                placeholder="Enter username"
                            />
                            <ErrorMessage name="username" component="div" className="error" />
                        </Form.Group>

                        <Row>
                            <Col md={6}>
                                <Form.Group controlId="password">
                                    <Form.Label>
                                        <FontAwesomeIcon icon={faLock} /> Password <span className="required">*</span>
                                    </Form.Label>
                                    <Field
                                        type="password"
                                        name="password"
                                        as={Form.Control}
                                        placeholder="Enter password"
                                    />
                                    <ErrorMessage name="password" component="div" className="error" />
                                </Form.Group>
                            </Col>
                            <Col md={6}>
                                <Form.Group controlId="confirmPassword">
                                    <Form.Label>
                                        <FontAwesomeIcon icon={faLock} /> Confirm Password{' '}
                                        <span className="required">*</span>
                                    </Form.Label>
                                    <Field
                                        type="password"
                                        name="confirmPassword"
                                        as={Form.Control}
                                        placeholder="Confirm password"
                                    />
                                    <ErrorMessage name="confirmPassword" component="div" className="error" />
                                </Form.Group>
                            </Col>
                        </Row>
                        {/* Email Address */}
                        <Form.Group controlId="email">
                            <Form.Label>
                                <FontAwesomeIcon icon={faEnvelope} /> Email Address <span className="required">*</span>
                            </Form.Label>
                            <Field
                                type="email"
                                name="email"
                                as={Form.Control}
                                placeholder="Enter email"
                            />
                            <ErrorMessage name="email" component="div" className="error" />
                        </Form.Group>
                        {/* Contact Number */}
                        <Form.Group controlId="contactNumber">
                            <Form.Label>
                                <FontAwesomeIcon icon={faPhone} /> Contact Number <span className="required">*</span>
                            </Form.Label>
                            <Field
                                type="tel"
                                name="contactNumber"
                                as={Form.Control}
                                placeholder="Enter contact number"
                            />
                            <ErrorMessage name="contactNumber" component="div" className="error" />
                        </Form.Group>
                        <Button
                            variant="primary"
                            type="submit"
                            className="btn-primary"
                            disabled={isSubmitting}
                        >
                            {isSubmitting ? 'Registering...' : 'Register'}
                        </Button>
                        <div className="mt-3 text-center">
                            <span>Already have an account? </span>
                            <Link to="/login">Login</Link>
                        </div>
                    </Form>
                )}
            </Formik>
        </div>
    );
};

export default RegistrationForm;
