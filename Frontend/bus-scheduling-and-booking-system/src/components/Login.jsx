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
import { useNavigate, Link } from 'react-router-dom';
import { useUser } from './UserContext';

const LoginForm = () => {
    const navigate = useNavigate(); // Initialize useHistory
    const initialValues = {
        username: '',
        password: '',
    };
    const { setUserRole, setJwtToken, setUserName } = useUser();

    const validationSchema = Yup.object().shape({
        username: Yup.string().required('Username is required'),
        password: Yup.string().required('Password is required'),
    });

    const handleSubmit = async (values, { setSubmitting }) => {
        try {
            const response = await AuthService.login(values);

            if (response.status === 200) {
                setSubmitting(false);

                const username = response.data.username;
                const role = response.data.userRole;
                const jwtToken = response.data.jwtToken;

                // Store the JWT token and user role in local storage
                localStorage.setItem('jwtToken', jwtToken);
                localStorage.setItem('userRole', role);
                localStorage.setItem('username', username);

                // Set JWT token and user role in the context
                setJwtToken(jwtToken);
                setUserRole(role);
                setUserName(username);

                // Check user role and navigate accordingly
                if (role === 'Admin') {
                    Modal.success({
                        title: "Login Successful",
                        content: (
                            <p><strong>Welcome, {response.data.username}</strong></p>
                        )
                    });
                    // Navigate to the admin page (Sidebar)
                    navigate('/admin/'); // You can update this route as needed
                } else if (role === 'User') {
                    Modal.success({
                        title: "Login Successful",
                        content: (
                            <p><strong>Welcome, {response.data.username}</strong></p>
                        )
                    });
                    // Navigate to the user's wallet page
                    navigate('/');
                } else {
                    // Handle other roles or unknown roles
                    Modal.error({
                        title: "Login Failed",
                        content: (
                            <p>Unknown user role. Please contact the administrator.</p>
                        )
                    });
                }
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
            console.error(error);
            // console.log(error.response);
            Modal.error({
                title: "Error",
                content: (
                    <p>{error.response?.data || 'Error logging in.'}</p>
                )
            });
        }
    };

    return (
        <div className="login-container">
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
            <div className="mt-3 text-center">
                <span>Don't have an account? </span>
                <Link to="/reg">Register</Link>
            </div>
        </div>
    );
};

export default LoginForm;
