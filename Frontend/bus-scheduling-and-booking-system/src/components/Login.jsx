import React, { useState } from 'react';
import { Form, Button, Container, Row, Col } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
    faUser,
    faLock,
} from '@fortawesome/free-solid-svg-icons';
import { Formik, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';

import Modal from 'antd/es/modal/index';

import '../css/LoginForm.css'; // Import your CSS file here

import AuthService from '../services/Auth.service';
import { useNavigate } from 'react-router-dom';


const LoginForm = () => {
    const navigate = useNavigate(); // Initialize useHistory
    const initialValues = {
        username: '',
        password: '',
    };

    const validationSchema = Yup.object().shape({
        username: Yup.string().required('Username is required'),
        password: Yup.string().required('Password is required'),
    });

    const handleSubmit = async (values, { setSubmitting }) => {
        try {

            const response = await AuthService.login(values);

            if (response.status === 200) {
                setSubmitting(false);
                console.log(response.data.jwtToken)
                const username = response.data.username
                Modal.success({
                    title: "Login Successful",
                    content: (
                        <p><strong>Welcome, {response.data.username}</strong></p>
                    )
                });
                navigate('/wallet',{state:{username}})
            } else {
                setSubmitting(false);
                Modal.error({
                    title: "Login Failed",
                    content: (
                        <p>Failed to login. Please check your credentials and try again.</p>
                    )
                });
            }
        } catch (error) {
            console.log(error.response);
            Modal.error({

                title: "Error",
                content: (
                    <p>{error.response?.data || 'Error logging in.'}</p>
                )
            });
        }
    };

    return (
        <Container className="login-container">
            <div className="text-center">
                <FontAwesomeIcon icon={faUser} />
                <h3 className="mt-2">User Login</h3>
            </div>
            <Formik
                initialValues={initialValues}
                validationSchema={validationSchema}
                onSubmit={handleSubmit}
            >
                {({ handleSubmit, isSubmitting }) => (
                    <Form onSubmit={handleSubmit}>
                        {/* Username and Password */}
                        <Row>
                            <Col md={12}>
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
                            </Col>
                        </Row>
                        <Row>
                            <Col md={12}>
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
                        </Row>
                        <Button
                            variant="primary"
                            type="submit"
                            className="btn-primary"
                            disabled={isSubmitting}
                        >
                            {isSubmitting ? 'Logging in...' : 'Login'}
                        </Button>
                    </Form>
                )}
            </Formik>
        </Container>
    );
};

export default LoginForm;
