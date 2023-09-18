import React, { useState } from 'react';
import { Button, Container, Grid, Paper, TextField } from '@mui/material';
import './HomeSearch.css'; // Import the external CSS file

function HomeSearch() {
  const [sourceCity, setSourceCity] = useState('');
  const [destinationCity, setDestinationCity] = useState('');
  const [selectedDate, setSelectedDate] = useState('');

  const handleDateChange = (event) => {
    setSelectedDate(event.target.value);
  };

  const handleSearch = () => {
    // Add logic to search for buses based on source, destination, and date
    console.log(`Searching for buses from ${sourceCity} to ${destinationCity} on ${selectedDate}`);
  };

  return (
<>
    <Container>
      <Grid container className="home-container">
        <Paper elevation={3} className="paper">
          <div className="horizontal-form">
            <TextField
            
              label="Source City"
              variant="outlined"
              fullWidth
              className="input"
              value={sourceCity}
              onChange={(e) => setSourceCity(e.target.value)}
            />
            <TextField
              label="Destination City"
              variant="outlined"
              fullWidth
              className="input"
              value={destinationCity}
              onChange={(e) => setDestinationCity(e.target.value)}
            />
            <TextField
              label="Journey Date"
              type="date"
              variant="outlined"
              InputLabelProps={{
                shrink: true,
              }}
              fullWidth
              className="input"
              value={selectedDate}
              onChange={handleDateChange}
            />
            <Button
              variant="contained"
              fullWidth
              onClick={handleSearch}
              className="search-button"
            >
              Search Buses
            </Button>
          </div>
        </Paper>
      </Grid>
    </Container>
    </>
  );
 
}

export default HomeSearch;
