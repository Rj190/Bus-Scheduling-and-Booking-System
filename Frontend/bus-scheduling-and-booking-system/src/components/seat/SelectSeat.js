import React, { useState, useEffect } from 'react';
import { useParams,useNavigate } from 'react-router-dom';
import axios from 'axios';
import {
  Grid,
  Paper,
  Button,
  Snackbar,
  Card,
  CardContent,
  Typography,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  TextField,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
} from '@mui/material';
import MuiAlert from '@mui/material/Alert';
import AirlineSeatReclineNormalIcon from '@mui/icons-material/AirlineSeatReclineNormal';
import FlightClassIcon from '@mui/icons-material/FlightClass';
import LockIcon from '@mui/icons-material/Lock';
import './Seats.css';

import PassengerForm from './PassengerForm';

import { Modal } from 'antd';

function Seats() {
  const { journeyId } = useParams();
  const [seatData, setSeatData] = useState([]);
  const [selectedSeats, setSelectedSeats] = useState([]);
  const [message, setMessage] = useState('');
  const [open, setOpen] = useState(false);
  const [fare, setFare] = useState('');
  const [totalFare, setTotalFare] = useState('');
  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [passengerForms, setPassengerForms] = useState([]);
  const [data, setData] = useState([]);
  const navigate = useNavigate();
  const [city ,setCity] = useState('');

  useEffect(() => {

    const username = localStorage.getItem('username');
    if (!username) {
      // Show a popup if username is null
      Modal.error({
        title: 'Error',
        content: 'Please log in.',
        onOk: () => navigate('/login'), // Navigate to login page
      });
      return;
    }
    async function fetchSeatData() {
      try {
        const response = await axios.get(`http://localhost:8080/api/journeys/${journeyId}`);
        if (response && response.data) {
          setData(response.data);
          setCity(response.data.route.departureCity +"- "+response.data.route.arrivalCity);
          const seats = response.data.bus.seats;
          setFare(response.data.fare);
          setSeatData(seats);
        } else {
          console.error('Invalid API response data');
        }
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    }
    fetchSeatData();
  }, [journeyId]);

  const isSeatAvailable = (seatNo) => {
    return seatData.find((seat) => seat.seatNumber === seatNo)?.seatAvailabilityStatus === 'Available';
  };

  const isSeatSelected = (seatNo) => {
    return selectedSeats.includes(seatNo);
  };

  const isSeatBooked = (seatNo) => {
    return seatData.find((seat) => seat.seatNumber === seatNo)?.seatAvailabilityStatus === 'Booked';
  };

  const handleSeatClick = (seatNo) => {
    if (selectedSeats.includes(seatNo)) {
      // If the seat is already selected, deselect it
      setSelectedSeats((prevSelectedSeats) => prevSelectedSeats.filter((seat) => seat !== seatNo));
    } else if (selectedSeats.length < 5) {
      // If less than 5 seats are selected, select the clicked seat
      setSelectedSeats((prevSelectedSeats) => [...prevSelectedSeats, seatNo]);
    } else {
      // If the user tries to select more than 5 seats, display a message
      setMessage('You can select a maximum of 5 seats.');
      setOpen(true);
    }
  };

  const handlePayment = () => {
    if (selectedSeats.length > 0) {
      // Initialize the passenger forms array with data for the selected seats
      const initialPassengerForms = selectedSeats.map((seatNo) => ({
        
        seatNumber: seatNo,
        busId : data.bus.busId,
        seat :data.bus.seats.find((seat) => seat.seatNumber == seatNo),
        passengerData: {
          passengerName: '',
          passengerLastName: '',
          passengerAge: '',
          passengerGender: '',
          passengerMobileNo: '',
          passengerEmail: '',
        },
      }));

      console.log(initialPassengerForms);



      // Set the passenger forms in state
      setPassengerForms(initialPassengerForms);

      // Open the dialog for entering passenger information
      setIsDialogOpen(true);
    } else {
      setMessage('Please select at least one seat.');
      setOpen(true);
    }
  };

  const handleDialogClose = () => {
    setIsDialogOpen(false);
  };

  const handleConfirmPayment = async () => {
    // Validate that passenger data is provided for all selected seats
    const isPassengerDataValid = passengerForms.every(
      (form) =>
        form.passengerData.passengerName &&
        form.passengerData.passengerLastName &&
        form.passengerData.passengerAge &&
        form.passengerData.passengerGender &&
        form.passengerData.passengerMobileNo &&
        form.passengerData.passengerEmail
    );

    if (isPassengerDataValid) {
      // Calculate the total fare based on the number of selected seats
      const calculatedTotalFare = selectedSeats.length * fare;
      setTotalFare(calculatedTotalFare);

      // Store passenger data and bus ID in local storage for payment page
      localStorage.setItem('passengerData', JSON.stringify(passengerForms));
      localStorage.setItem('journeyId', journeyId);
      localStorage.setItem('totalFare', calculatedTotalFare);

     // console.log(passengerForms);
      // Redirect to the payment page if needed
      navigate('/payment');
    } else {
      setMessage('Please provide passenger details for all selected seats.');
      setOpen(true);
    }
  };

  const handleClose = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }
    setOpen(false);
  };

  const handlePassengerDataChange = (seatNo, field, value) => {
    const formIndex = passengerForms.findIndex((form) => form.seatNumber === seatNo);

    if (formIndex !== -1) {
      const updatedPassengerForms = [...passengerForms];
      updatedPassengerForms[formIndex].passengerData[field] = value;
      setPassengerForms(updatedPassengerForms);
    }
  };


  useEffect(() => {
    // Calculate the total fare based on the number of selected seats
    const numberOfSeats = selectedSeats.length;
    const calculatedTotalFare = fare * numberOfSeats;
    setTotalFare(`Total Fare: ₹${calculatedTotalFare}`);
  }, [selectedSeats, fare]);

  const renderSeats = () => {
    const sortedSeats = [...seatData].sort((a, b) => {
      const seatANumber = parseInt(a.seatNumber.slice(1));
      const seatBNumber = parseInt(b.seatNumber.slice(1));
      return seatANumber - seatBNumber;
    });

    return (
      <div className="seat-grid">
        {sortedSeats.map((seat, index) => (
          <Paper
            key={seat.seatId}
            onClick={() => handleSeatClick(seat.seatNumber)}
            className={`seat ${isSeatSelected(seat.seatNumber)
              ? 'selected'
              : isSeatAvailable(seat.seatNumber)
              ? 'available'
              : isSeatBooked(seat.seatNumber)
              ? 'booked'
              : ''}`}
            style={{ cursor: isSeatAvailable(seat.seatNumber) ? 'pointer' : 'not-allowed' }}
          >
            <span className="seat-icon" onClick={(e) => e.stopPropagation()}>
              {isSeatSelected(seat.seatNumber) ? (
                <LockIcon fontSize="large" />
              ) : isSeatAvailable(seat.seatNumber) ? (
                <FlightClassIcon fontSize="large" style={{ color: 'green' }} />
              ) : isSeatBooked(seat.seatNumber) ? (
                <AirlineSeatReclineNormalIcon fontSize="large" style={{ color: 'red' }} />
              ) : (
                <AirlineSeatReclineNormalIcon fontSize="large" />
              )}
            </span>
            {seat.seatNumber}
          </Paper>
        ))}
      </div>
    );
  };

  return (
    <div className="mainContainer">
      <h1>{city}</h1>
      <h2>Select Seats</h2>
      <div className="container">
        {renderSeats()}
        {selectedSeats.length > 0 && (
          <Card className="paymentContainer">
            <CardContent>
              <Typography variant="h5" component="div" style={{ marginBottom: '20px', fontFamily: 'ModernFont' }}>
                Selected Seats
              </Typography>
              <ul>
                {selectedSeats.map((seatNo) => (
                  <li key={seatNo}>Seat {seatNo}</li>
                ))}
              </ul>
              <p>{totalFare}</p>
              <Button variant="contained" color="primary" onClick={handlePayment}>
                Pay for Seats
              </Button>
            </CardContent>
          </Card>
        )}
      </div>
      <div className="legend">
        <FlightClassIcon fontSize="large" style={{ color: 'green' }} />
        <span>Available</span>
        <LockIcon fontSize="large" />
        <span>Selected</span>
        <AirlineSeatReclineNormalIcon fontSize="large" style={{ color: 'red' }} />
        <span>Booked</span>
      </div>
      <Snackbar open={open} autoHideDuration={6000} onClose={handleClose}>
        <MuiAlert elevation={6} variant="filled" onClose={handleClose} severity="warning">
          {message}
        </MuiAlert>
      </Snackbar>
      <PassengerForm
        isOpen={isDialogOpen}
        onClose={handleDialogClose}
        passengerForms={passengerForms}
        handlePassengerDataChange={handlePassengerDataChange}
        handleConfirmPayment={handleConfirmPayment}
      />
    </div>
  );
}

export default Seats;
