import React, { useState, useRef } from 'react';
import { Button, Container, Grid, Paper, TextField, IconButton } from '@mui/material';
import SwapHorizIcon from '@mui/icons-material/SwapHoriz';
import citiesData from "./cities.json";
import './HomeSearch.css';
import axios from 'axios';
import { useNavigate, Route, Routes } from 'react-router-dom';
import JourneyDetails from './JourneyDetails';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';




function HomeSearch() {
  const [sourceCity, setSourceCity] = useState('');
  const [destinationCity, setDestinationCity] = useState('');
  const [selectedDate, setSelectedDate] = useState('');
  const [sourceSuggestions, setSourceSuggestions] = useState([]);
  const [destinationSuggestions, setDestinationSuggestions] = useState([]);
  const [focusedInput, setFocusedInput] = useState(null);
  const sourceInputRef = useRef(null);
  const destinationInputRef = useRef(null);
  const sourceSuggestionClicked = useRef(false);
  const destinationSuggestionClicked = useRef(false);
  const [errorText, setErrorText] = useState('');
  const [journeys, setJourneys] = useState([]);
  const navigate = useNavigate();




  const handleDateChange = (event) => {
    setErrorText('');
    setSelectedDate(event.target.value);
  };
  const handleSearch = async () => {
    if (sourceCity === destinationCity) {
      setErrorText("Source and destination cannot be the same city.");
    } else if (!isDateValid(selectedDate)) {
      setErrorText("Invalid date. You cannot book for past dates.");
    } else {
      try {
        const response = await axios.get(`http://localhost:8080/api/journeys/byDateAndCities/${selectedDate}/${sourceCity}/${destinationCity}`);
        
        if (response.status == 200) {
          if (response.data.length > 0) {
            setJourneys(response.data);
            navigate('/journey-details', { state: { journeys: response.data } });
          } else {
            toast.error("No journeys found for the selected criteria.", { autoClose: 5000 });
          }
        } else if (response.status == 404) {
          toast.error("No Buses Found.", { autoClose: 5000 });
        } else {
          toast.error("An error occurred while fetching journey information.", { autoClose: 5000 });
        }
      } catch (error) {
        console.error(error);
        toast.error("No Buses Found.", { autoClose: 5000 });
        toast.error("An error occurred while fetching journey information.", { autoClose: 5000 });
      }
    }
  };
  

  const getSuggestions = (inputValue) => {
    const inputValueLower = inputValue.toLowerCase();
    const filteredCities = citiesData.filter((city) =>
      city.val.toLowerCase().includes(inputValueLower)
    );

    return filteredCities;
  };

  const handleSourceInputChange = (e) => {
    setErrorText('');
    setSourceCity(e.target.value);
    setSourceSuggestions(getSuggestions(e.target.value));
    setDestinationSuggestions([]); // Clear destination suggestions when modifying source
    sourceSuggestionClicked.current = false;
  };

  const handleDestinationInputChange = (e) => {
    setErrorText('');
    setDestinationCity(e.target.value);
    setDestinationSuggestions(getSuggestions(e.target.value));
    setSourceSuggestions([]); // Clear source suggestions when modifying destination
    destinationSuggestionClicked.current = false;
  };

  const handleSourceSuggestionClick = (suggestion) => {
    setSourceCity(suggestion.val);
    setSourceSuggestions([]);
    sourceInputRef.current.focus();
    sourceSuggestionClicked.current = true;
  };

  const handleDestinationSuggestionClick = (suggestion) => {
    setDestinationCity(suggestion.val);
    setDestinationSuggestions([]);
    destinationInputRef.current.focus();
    destinationSuggestionClicked.current = true;
  };

  const handleInputFocus = (inputField) => {
    setFocusedInput(inputField);
  };

  const handleSwapCities = () => {
    const temp = sourceCity;
    setSourceCity(destinationCity);
    setDestinationCity(temp);
  };

  const isDateValid = (date) => {
    const currentDate = new Date();
    currentDate.setDate(currentDate.getDate() - 1); // Allow booking for today and future dates
    const selectedDate = new Date(date);
    return selectedDate >= currentDate;
  };

  return (
    <>
      <Container>
        <Grid container className="home-container">
          <Paper elevation={3} className="paper">
            <div className="horizontal-form">
              <TextField
                label="Source City"
                type="text"
                variant="outlined"
                fullWidth
                className="input"
                value={sourceCity}
                inputRef={sourceInputRef}
                onFocus={() => handleInputFocus('source')}
                onChange={handleSourceInputChange}
                error={sourceCity === destinationCity && sourceCity !== ''}
                helperText={sourceCity === destinationCity && sourceCity !== '' ? "Source and destination cannot be the same city." : ''}
              />
              {focusedInput === 'source' && !sourceSuggestionClicked.current && (
                <ul className="suggestions-list">
                  {sourceSuggestions.map((suggestion) => (
                    <li
                      key={suggestion.key}
                      onClick={() => handleSourceSuggestionClick(suggestion)}
                    >
                      {suggestion.val}
                    </li>
                  ))}
                </ul>
              )}
              <IconButton onClick={handleSwapCities}>
                <SwapHorizIcon />
              </IconButton>
              <TextField
                label="Destination City"
                type="text"
                variant="outlined"
                fullWidth
                className="input"
                value={destinationCity}
                inputRef={destinationInputRef}
                onFocus={() => handleInputFocus('destination')}
                onChange={handleDestinationInputChange}
                error={sourceCity === destinationCity && destinationCity !== ''}
                helperText={sourceCity === destinationCity && destinationCity !== '' ? "Source and destination cannot be the same city." : ''}
              />
              {focusedInput === 'destination' && !destinationSuggestionClicked.current && (
                <ul className="suggestions-list">
                  {destinationSuggestions.map((suggestion) => (
                    <li
                      key={suggestion.key}
                      onClick={() => handleDestinationSuggestionClick(suggestion)}
                    >
                      {suggestion.val}
                    </li>
                  ))}
                </ul>
              )}
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
                error={!isDateValid(selectedDate) && selectedDate !== '' && errorText === ''}
                helperText={!isDateValid(selectedDate) && selectedDate !== '' ? "Invalid date. You cannot book for past dates." : errorText}
              />
              <Button
                variant="contained"
                fullWidth
                onClick={handleSearch}
                className="search-button"
                disabled={!sourceCity || !destinationCity || !isDateValid(selectedDate) || errorText}
              >
                Search Buses
              </Button>
            </div>
          </Paper>
        </Grid>

      </Container>
      <ToastContainer position="top-right" autoClose={5000} hideProgressBar={false} newestOnTop closeOnClick rtl={false} pauseOnFocusLoss draggable pauseOnHover />
    </>
  );
}

export default HomeSearch;
