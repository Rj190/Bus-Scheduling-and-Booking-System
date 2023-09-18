// Seat.js
import React from 'react';
import { Paper } from '@mui/material';

function Seat({ seat, isSeatSelected, isSeatAvailable, isSeatBooked, handleSeatClick }) {
  return (
    <Paper
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
      {seat.seatNumber}
    </Paper>
  );
}

export default Seat;
