import React, { useState, useEffect } from 'react';
import axios from 'axios';
import JourneyService from '../services/Journey.service';
import UserService from '../services/User.service';
import { Modal, Button, InputNumber } from 'antd'; // Import InputNumber from antd
import { useNavigate } from 'react-router-dom';
import PaymetService from '../services/Paymet.service';
import WalletService from '../services/Wallet.Service';
import PassengerService from '../services/Passenger.Service';
import BookingService from '../services/Booking.Service';
import BookingDetailsService from '../services/BookingDetails.Service';
import jsPDF from 'jspdf'; // Import jsPDF for generating PDFs

import '../css/Payment.css'; // Import your custom CSS file for styling

function Payment() {
    const navigate = useNavigate();
    const [passengerData, setPassengerData] = useState([]);
    const totalFare = localStorage.getItem('totalFare');
    const journeyId = localStorage.getItem('journeyId');
    const [paymentStatus, setPaymentStatus] = useState('');
    const [journey, setJourney] = useState(null); // Initialize journey as null
    const [bookingId, setBookingId] = useState(null);
    const [paymentId, setPaymentId] = useState(null);
    const [showPaymentButton, setShowPaymentButton] = useState(true); // Control the visibility of the Pay Now button

    useEffect(() => {
        // Retrieve passenger data from local storage
        const storedPassengerData = localStorage.getItem('passengerData');
        fetchJourney(journeyId);
        if (storedPassengerData) {
            const parsedPassengerData = JSON.parse(storedPassengerData);
            setPassengerData(parsedPassengerData);
        }
    }, []); // Empty dependency array to run this effect only once

    const handlePayment = async () => {
        try {
            const user = await UserService.getUserByUserName(localStorage.getItem('username'));
            const balance = user.data.wallet.walletBalance;
            console.log(balance);
            if (!user?.data) {
                // Show an error message if the user's wallet is not found
                Modal.error({
                    title: 'Error',
                    content: 'Wallet not found. Please create a wallet first.',
                    onOk: () => navigate('/'), // Navigate to home or login page
                });
                return;
            }

            const check = balance < totalFare;
            console.log(check);

            if (!check) {
                // Calculate the current date in "yyyy-mm-dd" format
                const currentDate = new Date();
                const year = currentDate.getFullYear();
                const month = (currentDate.getMonth() + 1).toString().padStart(2, '0'); // Ensure two digits for month
                const day = currentDate.getDate().toString().padStart(2, '0'); // Ensure two digits for day
                const formattedDate = `${year}-${month}-${day}`;

                // Insert payment record
                const paymentData = {
                    paymentDate: formattedDate,
                    paymentAmount: totalFare,
                    payementStatus: 'success',
                };

                const paymentResponse = await PaymetService.insertPayment(localStorage.getItem('username'), paymentData);

                console.log(paymentResponse);

                if (paymentResponse.status === 200) {

                    // Deduct the payment amount from the wallet balance
                    const updatedWallet = await WalletService.updateWallet(user.data.wallet.walletId, {
                        walletBalance: user.data.wallet.walletBalance - totalFare,
                    });


                    const seatAndPassenger = [];
                    // Create passengers and collect responses in an array
                    const passengerResponses = await Promise.all(
                        passengerData.map(async (passenger) => {
                            const passengerResponse = await PassengerService.createPassenger(passenger.passengerData);
                            seatAndPassenger.push(
                                {
                                    passengerId: passengerResponse.data.passengerId,
                                    seatId : passenger.seat.seatId
                                
                                }
                                )
                            return passengerResponse.data // Assuming your createPassenger method returns the created passenger
                        })
                    );

                    // Create a booking object
                    const bookingData = {
                        bookingDate: formattedDate,
                        bookingStatus: 'success',
                        bookingTotalFare: totalFare,
                        bookingNumPassengers: passengerResponses.length, // Number of passenger
                    };

                    console.log(bookingData);

                    //   Create the booking
                    const bookingResponse = await BookingService.saveBooking(
                        localStorage.getItem('username'),
                        journeyId, // Replace with your actual journey ID
                        paymentResponse.data.paymentId, // Use the payment ID from the payment response
                        bookingData
                    );


                    console.log(bookingResponse)

                    console.log(seatAndPassenger)

                    const BookingDetailsResponses = await Promise.all(
                    seatAndPassenger.map(async (passenger) => {
                        const passengerResponse = await BookingDetailsService.createBookingDetails(bookingResponse.data.bookingId,passenger.seatId,passenger.passengerId);
                        return passengerResponse.data; // Assuming your createPassenger method returns the created passenger
                    })
                    );

                    console.log(BookingDetailsResponses);

                    // Payment successful
                    setPaymentStatus('Payment Successful');
                    setBookingId(bookingResponse.data.bookingId);
                    setPaymentId(paymentResponse.data.paymentId);
                    // Hide the Pay Now button
                    setShowPaymentButton(false);
                } else {
                    setPaymentStatus('Payment Failed (Failed to Insert Payment Record)');
                }
            } else {
                setPaymentStatus('Payment Failed (Insufficient Balance)');
                return;
            }
        } catch (error) {
            console.error('Payment error:', error);
            setPaymentStatus('Payment Failed');
        }
    };


    const fetchJourney = async (journeyId) => {
        try {
            const journey = await JourneyService.getJourneyById(journeyId);
            setJourney(journey.data);
            console.log("Journey : ", journey.data);
        } catch (error) {
            console.error('Error fetching journeys:', error);
        }
    };

    // Function to generate and download the PDF ticket
    const downloadTicketPDF = () => {
        const doc = new jsPDF();

        // Set background color and border for the ticket
        doc.setFillColor(255, 255, 255); // White background
        doc.setDrawColor(0, 0, 0); // Black border
        doc.rect(10, 10, 190, 270, 'FD'); // Draw a filled rectangle with a border

        // Add a header with a title
        doc.setFontSize(16);
        doc.text('Ticket Details', 80, 25);

        // Add a separator line
        doc.setLineWidth(0.5);
        doc.line(10, 30, 200, 30);

        // Add content to the ticket
        doc.setFontSize(12);

        // Booking and payment details
        doc.text(`Booking ID: ${bookingId}`, 20, 45);
        doc.text(`Payment ID: ${paymentId}`, 20, 60);
        doc.text('Payment Details:', 20, 75);
        doc.text(`Total Amount: ₹${totalFare}`, 20, 90);

        // Passenger Information
        doc.text('Passenger Information:', 20, 100);
        passengerData.forEach((passenger, index) => {
            doc.text(`Passenger ${index + 1}:`, 40, 115 + index * 60);
            doc.text(`Seat ${passenger.seatNumber}:`, 50, 125 + index * 60);
            doc.text(`Name: ${passenger.passengerData.passengerName}`, 50, 140 + index * 60);
            doc.text(`Last Name: ${passenger.passengerData.passengerLastName}`, 50, 155 + index * 60);
            doc.text(`Age: ${passenger.passengerData.passengerAge}`, 50, 170 + index * 60);
            doc.text(`Gender: ${passenger.passengerData.passengerGender}`, 50, 185 + index * 60);
            doc.text(`Mobile No: ${passenger.passengerData.passengerMobileNo}`, 50, 200 + index * 60);
            doc.text(`Email: ${passenger.passengerData.passengerEmail}`, 50, 215 + index * 60);
        });

        // Save the PDF with a filename (using booking ID)
        doc.save(`${bookingId}.pdf`);
    };

    return (
        <div className="payment-container">
            <h2>Payment Details</h2>
            {/* Conditional check to ensure journey is defined before accessing its properties */}
            {journey && (
                <div>
                    <p>Journey : {journey.route.departureCity} -  {journey.route.arrivalCity}</p>
                    <p>Bus : {journey.bus.busName}</p>
                    <p>Class : {journey.bus.busType}</p>
                </div>
            )}
            <p>Total Amount : ₹{totalFare}</p>
            {showPaymentButton && (
                <button className="payment-button" onClick={handlePayment}>
                    Pay Now
                </button>
            )}
            {paymentStatus && (
                <div>
                    <p className="payment-status">{paymentStatus}</p>
                    {/* Display Booking and Payment IDs */}
                    {bookingId && paymentId && (
                        <div>
                            <p>Booking ID: {bookingId}</p>
                            <p>Payment ID: {paymentId}</p>
                            {/* Download Ticket button */}
                            <button className="payment-button" onClick={downloadTicketPDF}>
                                Download Ticket
                            </button>
                        </div>
                    )}
                </div>
            )}

            <h2>Selected Seats and Passenger Information</h2>
            <div className={passengerData.length >= 3 ? 'passenger-grid' : 'passenger-grid single-column'}>
                {passengerData.map((passenger, index) => (
                    <div key={index} className="passenger-info">
                        <h3>Seat {passenger.seatNumber}</h3>
                        <p>Name: {passenger.passengerData.passengerName}</p>
                        <p>Last Name: {passenger.passengerData.passengerLastName}</p>
                        <p>Age: {passenger.passengerData.passengerAge}</p>
                        <p>Gender: {passenger.passengerData.passengerGender}</p>
                        <p>Mobile No: {passenger.passengerData.passengerMobileNo}</p>
                        <p>Email: {passenger.passengerData.passengerEmail}</p>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default Payment;
