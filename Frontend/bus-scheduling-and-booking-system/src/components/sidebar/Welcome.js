import React from 'react';
import '../../css/Bus.css'
import { useEffect } from 'react';
import { useState } from 'react';



function Welcome() {
    const [username,setUserName] = useState('');

    useEffect(() => {
        setUserName(localStorage.getItem('username'));
    },[]);
    return (
        <div className='side-container'>
            <div className="welcome-message">
            Welcome To Admin Page {username}
            </div>
          
        </div>

    );

}

export default Welcome;