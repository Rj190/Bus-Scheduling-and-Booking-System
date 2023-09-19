import React from 'react';
import './Home.css'; // Import your CSS file here
import HomeSearch from './HomeSearch';

function Home() {
  return (
    <div className='home'>
      <div className="welcome-message">Welcome to Bus Booking!</div>
      <HomeSearch />
    </div>
  );
}

export default Home;
