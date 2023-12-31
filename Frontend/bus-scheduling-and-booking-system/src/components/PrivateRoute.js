import React from 'react';
import { Route, Navigate } from 'react-router-dom';
import { useUser } from './UserContext';

const PrivateRoute = ({ path, element }) => {
  const userContext = useUser();

  return userContext ? (
    <Route path={path} element={element} />
  ) : (
    <Navigate to="/login" />
  );
};

export default PrivateRoute;
