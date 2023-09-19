import { createContext, useContext, useState, useEffect } from 'react';

const UserContext = createContext();

export const UserProvider = ({ children }) => {
  const [userRole, setUserRole] = useState(null);
  const [jwtToken, setJwtToken] = useState(null);
  const [username, setUserName] = useState(null);
  const [isAuthenticated, setIsAuthenticated] = useState(false); // Add isAuthenticated state

  useEffect(() => {
    // Check local storage for JWT token and user role
    const storedJwtToken = localStorage.getItem('jwtToken');
    const storedUserRole = localStorage.getItem('userRole');
    const storedUsername = localStorage.getItem('username');

    if (storedJwtToken && storedUserRole && storedUsername) {
      setJwtToken(storedJwtToken);
      setUserRole(storedUserRole);
      setUserName(storedUsername);
      setIsAuthenticated(true); // Set isAuthenticated to true
    }
  }, []);

  return (
    <UserContext.Provider value={{ userRole, setUserRole, jwtToken, setJwtToken, username, setUserName, isAuthenticated ,setIsAuthenticated}}>
      {children}
    </UserContext.Provider>
  );
};

export const useUser = () => {
  const context = useContext(UserContext);
  if (!context) {
    throw new Error('useUser must be used within a UserProvider');
  }
  return context;
};
