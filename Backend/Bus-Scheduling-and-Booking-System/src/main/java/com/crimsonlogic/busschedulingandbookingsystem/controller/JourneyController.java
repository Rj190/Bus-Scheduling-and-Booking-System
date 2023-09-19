package com.crimsonlogic.busschedulingandbookingsystem.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Bus;
import com.crimsonlogic.busschedulingandbookingsystem.entity.Journey;
import com.crimsonlogic.busschedulingandbookingsystem.entity.Route;
import com.crimsonlogic.busschedulingandbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.exception.RouteNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.service.IBusService;
import com.crimsonlogic.busschedulingandbookingsystem.service.IJourneyService;
import com.crimsonlogic.busschedulingandbookingsystem.service.IRouteService;

@RestController
@RequestMapping("/api/journeys")
@CrossOrigin("*")
public class JourneyController {

	@Autowired
	private IJourneyService journeyService;

	@Autowired
	private IBusService busService;

	@Autowired
	private IRouteService routeService;

	@GetMapping("/all")
	public List<Journey> viewAllJourneys() {
		return journeyService.viewAllJourneys();
	}

	@PostMapping("/add/{busId}/{routeId}")
	public ResponseEntity<?> insertJourney(@Valid @RequestBody Journey journey, BindingResult bindingResult,
			@PathVariable("busId") Integer busId, @PathVariable("routeId") Integer routeId)
			throws RouteNotFoundException {
		Bus bus = busService.getBusById(busId).orElseThrow(() -> new ResourceNotFoundException("Bus", "Bus Id", busId));
		Route route = routeService.findRouteById(routeId);
		journey.setBus(bus);
		journey.setRoute(route);

		Journey createdJourney = journeyService.insertJourney(journey);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdJourney);
	}

	@GetMapping("/{journeyId}")
	public ResponseEntity<?> viewJourneyById(@PathVariable int journeyId) {
		try {
			Journey journey = journeyService.viewJourneyById(journeyId);
			return ResponseEntity.ok(journey);
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}

	@DeleteMapping("/delete/{journeyId}")
	public ResponseEntity<String> deleteJourney(@PathVariable int journeyId) {
		try {
			journeyService.deleteJourneyById(journeyId);
			return ResponseEntity.ok("Journey deleted successfully");
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}

	@PutMapping("/update/{journeyId}/{busId}/{routeId}")
	public ResponseEntity<?> updateJourneyById(@PathVariable int journeyId, @Valid @RequestBody Journey newJourney,@PathVariable("busId") Integer busId, @PathVariable("routeId") Integer routeId) throws RouteNotFoundException {

		try {
			Bus bus = busService.getBusById(busId).orElseThrow(() -> new ResourceNotFoundException("Bus", "Bus Id", busId));
			Route route = routeService.findRouteById(routeId);
			newJourney.setBus(bus);
			newJourney.setRoute(route);
			System.out.println(route);
			Journey updatedJourney = journeyService.updateJourneyById(journeyId, newJourney);
			return ResponseEntity.ok(updatedJourney);
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	
	
	

	@GetMapping("/all/by-journey-date/{journeyDate}")
    public List<Journey> viewJourneysByDate(@PathVariable("journeyDate") LocalDate journeyDate) {
        return journeyService.viewJourneysByDate(journeyDate);
        
        
    }
 
	@GetMapping("/all/by-journey-time/{startTime}/{endTime}")
    public List<Journey> viewJourneysByTime(@PathVariable("startTime") LocalTime startTime,@PathVariable("endTime")  LocalTime endTime) {
        return journeyService.viewJourneysByTime(startTime, endTime);
    }

	@GetMapping("/by-bus/{busId}")
    public ResponseEntity<?> viewJourneysByBus(@PathVariable("busId") Integer busId) {
		try {
			List<Journey> journeys = journeyService.viewJourneysByBus(busId);
			return ResponseEntity.ok(journeys);
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
    }
	
	
	
	@GetMapping("/by-route/{routeId}")
    public ResponseEntity<?> viewJourneysByRoute(@PathVariable("routeId") Integer routeId) {
    		try {
			List<Journey> journeys = journeyService.viewJourneysByRoute(routeId);
			return ResponseEntity.ok(journeys);
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}

    }

	@GetMapping("/byDateAndCities/{journeyDate}/{departureCity}/{arrivalCity}")
	public ResponseEntity<List<Journey>> findJourneysByDateAndCities(
			@PathVariable("journeyDate")  String journeyDate,
			@PathVariable("departureCity") String departureCity,
			@PathVariable("arrivalCity") String arrivalCity) {
		List<Journey> journeys = journeyService.findJourneysByDateAndCities(LocalDate.parse(journeyDate), departureCity, arrivalCity);

		if (journeys != null) {
			return ResponseEntity.ok(journeys);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	

	
	
	
	

	/**
	 * Handle RouteNotFoundException and return appropriate response.
	 * 
	 * @param ex RouteNotFoundException instance
	 * @return ResponseEntity with NOT_FOUND status and error message
	 */
//	@ExceptionHandler(RouteNotFoundException.class)
//	public ResponseEntity<String> handleRouteNotFoundException(RouteNotFoundException ex) {
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//	}
//
//	/**
//	 * Handle other exceptions and return INTERNAL_SERVER_ERROR status.
//	 * 
//	 * @param ex Exception instance
//	 * @return ResponseEntity with INTERNAL_SERVER_ERROR status and error message
//	 */
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<String> handleGeneralException(Exception ex) {
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
//	}
}
