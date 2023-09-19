import React, { useState, useEffect } from 'react';
import UserService from '../../services/User.service';
import BookingDetailsService from '../../services/BookingDetails.Service';
import '../../css/MyBookings.css';
import JourneyInfo from './JourneyInfo';
import IconButton from '@mui/material/IconButton';
import VisibilityIcon from '@mui/icons-material/Visibility';


function BookingCard({ bookingDetailsGroup }) {
    const [showDetails, setShowDetails] = useState(false);

    const toggleDetails = () => {
        setShowDetails(!showDetails);
    };

    // Create a unique key based on the booking ID
    const uniqueKey = `booking-card-${bookingDetailsGroup[0].booking.bookingId}`;

    return (
        <div className="booking-card" key={uniqueKey}>
            <h3>Booking ID: {bookingDetailsGroup[0].booking.bookingId}</h3>
            <p>Booking Date: {bookingDetailsGroup[0].booking.bookingDate}</p>
            <p>Total Fare: {bookingDetailsGroup[0].booking.bookingTotalFare}</p>
            <p>Number of Passengers: {bookingDetailsGroup[0].booking.bookingNumPassengers}</p>
            <IconButton onClick={toggleDetails} color="primary">
                <VisibilityIcon />
            </IconButton>
            {showDetails && (
                <>
                    {bookingDetailsGroup.map((bookingDetails, index) => (
                        <div key={index}>
                            <p>Seat: {bookingDetails.seat.seatNumber}</p>
                            <p>Passenger Name: {bookingDetails.passenger.passengerName}</p>
                            <JourneyInfo journey={bookingDetails.seat.bus.journeys[0]} />
                            {/* Add more booking details as needed */}
                        </div>
                    ))}
                </>
            )}
        </div>
    );
}



function MyBookings() {
    const [userBookings, setUserBookings] = useState([]);

    useEffect(() => {
        // Fetch user's bookings when the component mounts
        fetchUserBookings();
    }, []);

    const fetchUserBookings = async () => {
        try {
            // Replace 'username' with the actual username of the logged-in user
            const username = localStorage.getItem('username');
            const user = await UserService.getUserByUserName(username);

            // Create an array to store booking details
            const bookingDetailsArray = [];

            // Fetch booking details for each booking
            for (const booking of user.data.bookings) {
                const response = await BookingDetailsService.getBookingDetailsByBookingId(booking.bookingId);

                // Check if the booking ID is already in the array
                const existingGroupIndex = bookingDetailsArray.findIndex(
                    (group) => group[0].booking.bookingId === response.data[0].booking.bookingId
                );

                if (existingGroupIndex !== -1) {
                    // If a group with the same booking ID exists, push the details to that group
                    bookingDetailsArray[existingGroupIndex].push(...response.data);
                } else {
                    // Otherwise, create a new group for the booking ID
                    bookingDetailsArray.push(response.data);
                }
            }

            setUserBookings(bookingDetailsArray);
        } catch (error) {
            console.error('Error fetching user bookings:', error);
        }
    };

    return (
        <div className="my-bookings-container">
            <h2>My Bookings</h2>
            {userBookings.length === 0 ? (
                <p>No bookings available.</p>
            ) : (
                <div className="booking-cards">
                    {userBookings.map((bookingDetailsGroup, index) => (
                        <BookingCard key={index} bookingDetailsGroup={bookingDetailsGroup} />
                    ))}
                </div>
            )}
        </div>
    );
}

export default MyBookings;
