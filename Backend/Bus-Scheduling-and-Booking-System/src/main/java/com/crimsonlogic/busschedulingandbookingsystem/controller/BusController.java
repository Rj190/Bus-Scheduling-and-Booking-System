package com.crimsonlogic.busschedulingandbookingsystem.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import com.crimsonlogic.busschedulingandbookingsystem.entity.Bus;
import com.crimsonlogic.busschedulingandbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.exception.RouteNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.service.IBusService;

/**
 * Controller class to handle operations related to buses.
 * 
 * @author Karthik Jadhav
 * @author Rohit Jadhav
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping("api/bus")
public class BusController {

	@Autowired
	private IBusService busService;

	/**
	 * Get a list of all buses.
	 * 
	 * @return List of all buses
	 */
	@GetMapping("/listbuses")
	public List<Bus> getAllBuses() {
		return busService.getAllBuses();
	}

	/**
	 * Save a new bus.
	 * 
	 * @param bus           The bus to be saved
	 * @param bindingResult BindingResult for validation
	 * @return ResponseEntity with appropriate status and response body
	 */
	@PostMapping("/savebus")
	public ResponseEntity<?> saveBus(@Valid @RequestBody Bus bus, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// Handle validation errors
			StringBuilder errorMessage = new StringBuilder();
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage())
						.append("; ");
			}
			return ResponseEntity.badRequest().body(errorMessage.toString());
		}

		Bus savedBus = busService.saveBus(bus);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedBus);
	}

	/**
	 * Update a bus by its ID.
	 * 
	 * @param busId         The ID of the bus to be updated
	 * @param bus           The updated bus data
	 * @param bindingResult BindingResult for validation
	 * @return ResponseEntity with appropriate status and response body
	 */
	@PutMapping("/updatebus/{id}")
	public ResponseEntity<?> updateBus(@PathVariable("id") Integer busId, @Valid @RequestBody Bus bus,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// Handle validation errors
			StringBuilder errorMessage = new StringBuilder();
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage())
						.append("; ");
			}
			return ResponseEntity.badRequest().body(errorMessage.toString());
		}

		try {
			Bus updatedBus = busService.updateBus(bus, busId);
			return ResponseEntity.ok(updatedBus);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Get a bus by its ID.
	 * 
	 * @param id The ID of the bus
	 * @return ResponseEntity with appropriate status and response body
	 */
	@GetMapping("/findbus/{id}")
	public ResponseEntity<?> getBusById(@PathVariable Integer id) {
		try {
			Bus foundBus = busService.getBusById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Bus", "Bus Id", id));
			return ResponseEntity.ok(foundBus);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Delete a bus by its ID.
	 * 
	 * @param id The ID of the bus to be deleted
	 * @return ResponseEntity with appropriate status and response body
	 */
	@DeleteMapping("/deletebus/{id}")
	public ResponseEntity<String> deleteBusById(@PathVariable("id") Integer id) {
		try {
			String result = busService.deleteBusById(id);
			return ResponseEntity.ok(result);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Handle RouteNotFoundException and return appropriate response.
	 * 
	 * @param ex RouteNotFoundException instance
	 * @return ResponseEntity with NOT_FOUND status and error message
	 */
	@ExceptionHandler(RouteNotFoundException.class)
	public ResponseEntity<String> handleRouteNotFoundException(RouteNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	/**
	 * Handle other exceptions and return INTERNAL_SERVER_ERROR status.
	 * 
	 * @param ex Exception instance
	 * @return ResponseEntity with INTERNAL_SERVER_ERROR status and error message
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneralException(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	}
}
