

const apiKey = '5b3ce3597851110001cf6248f5b9c2b73deb4f2ba3f42f36ee988721'; // Replace with your API key
const departureCity = 'New York';
const arrivalCity = 'Los Angeles';

// Function to fetch coordinates for a city name
async function getCoordinates(cityName) {
  try {
    const response = await axios.get(
      `https://api.openrouteservice.org/geocode/search?api_key=${apiKey}&text=${encodeURIComponent(cityName)}`
    );

    if (response.data && response.data.features && response.data.features.length > 0) {
      const coordinates = response.data.features[0].geometry.coordinates;
      return coordinates;
    } else {
      throw new Error('City not found.');
    }
  } catch (error) {
    console.error('Error fetching coordinates:', error);
    throw error;
  }
}

// Fetch coordinates for departure and arrival cities
const departureCoordinates = await getCoordinates(departureCity);
const arrivalCoordinates = await getCoordinates(arrivalCity);

// Calculate distance and duration using coordinates
const response = await axios.get(
  `https://api.openrouteservice.org/v2/directions/driving-car?api_key=${apiKey}&start=${departureCoordinates[0]},${departureCoordinates[1]}&end=${arrivalCoordinates[0]},${arrivalCoordinates[1]}`
);

if (response.data && response.data.features && response.data.features.length > 0) {
  console.log(response.data.features)
  const route = response.data.features[0];
  const distance = route.properties.segments[0].distance / 1000; // Distance in kilometers
  const duration = route.properties.segments[0].duration / 3600; // Duration in hours

  console.log(`Distance: ${distance} km`);
  console.log(`Duration: ${duration} hours`);
} else {
  console.error('No route found.');
}
