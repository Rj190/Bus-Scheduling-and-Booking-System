import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import Logout from '../Logout';
import './NavBar.css'; // Import your custom CSS here

function NavBar() {
  const navigate = useNavigate();
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    // Check the user's authentication status when the component mounts
    const username = localStorage.getItem('username'); // You can use your authentication logic here

    if (username) {
      setIsAuthenticated(true);
    } else {
      setIsAuthenticated(false);
    }
  }, []);

  const handleSignIn = () => {
    navigate('/login');
  };

  const handleSignUp = () => {
    navigate('/reg');
  };

  return (
    <div className="navbar-container"> {/* Add your own class name */}
      <div className="navbar-logo">Bus Service</div> {/* Add your own class name */}
      <div className="navbar-links"> {/* Add your own class name */}
        {isAuthenticated ? (
          <>
            <div className="navbar-welcome">Welcome, {localStorage.getItem('username')}</div> {/* Add your own class name */}

            <Link to="/wallet" className="navbar-link">
              Wallet
            </Link> {/* Add your own class name */}
            <Link to="/bookings" className="navbar-link">
              My Bookings
            </Link> {/* Add your own class name */}
            <Link to="/" className="navbar-link">
              Book
            </Link> {/* Add your own class name */}
            <Logout />
          </>
        ) : (
          <>
            <button className="navbar-button" onClick={handleSignIn}> {/* Add your own class name */}
              Sign In
            </button>
            <button className="navbar-button" onClick={handleSignUp}> {/* Add your own class name */}
              Sign Up
            </button>
          </>
        )}
      </div>
    </div>
  );
}

export default NavBar;
