// PassengerForm.js

import React from 'react';
import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Typography,
  TextField,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  Button,
} from '@mui/material';

import './PassengerForm.css';


function PassengerForm({ isOpen, onClose, passengerForms, handlePassengerDataChange, handleConfirmPayment }) {
  return (
    <Dialog open={isOpen} onClose={onClose}>
      <DialogTitle>Passenger Information</DialogTitle>
      <DialogContent>
        <div className="passenger-form">
          {passengerForms.map((form, index) => (
            <div key={form.seatNumber} className="passenger-form-field">
              <Typography variant="h6" gutterBottom>
                Passenger {index + 1} for {form.seatNumber}
              </Typography>
              <TextField
                label="Name"
                fullWidth
                onChange={(e) => handlePassengerDataChange(form.seatNumber, 'passengerName', e.target.value)}
              />
            
              <TextField
                label="Last Name"
                fullWidth
                onChange={(e) => handlePassengerDataChange(form.seatNumber, 'passengerLastName', e.target.value)}
              />
              <TextField
                label="Age"
                type="number"
                fullWidth
                onChange={(e) => handlePassengerDataChange(form.seatNumber, 'passengerAge', e.target.value)}
              />
              <FormControl fullWidth>
                <InputLabel>Gender</InputLabel>
                <Select onChange={(e) => handlePassengerDataChange(form.seatNumber, 'passengerGender', e.target.value)}>
                  <MenuItem value="Male">Male</MenuItem>
                  <MenuItem value="Female">Female</MenuItem>
                </Select>
              </FormControl>
              <TextField
                label="Mobile Number"
                fullWidth
                onChange={(e) => handlePassengerDataChange(form.seatNumber, 'passengerMobileNo', e.target.value)}
              />
              <TextField
                label="Email"
                fullWidth
                onChange={(e) => handlePassengerDataChange(form.seatNumber, 'passengerEmail', e.target.value)}
              />
            </div>
          ))}
        </div>
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose} color="primary">
          Cancel
        </Button>
        <Button onClick={handleConfirmPayment} color="primary">
          Confirm Payment
        </Button>
      </DialogActions>
    </Dialog>
  );
}

export default PassengerForm;
