package com.crimsonlogic.busschedulingandbookingsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Route;
import com.crimsonlogic.busschedulingandbookingsystem.exception.RouteNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.service.IRouteService;

/**
 * Controller class to handle operations related to routes.
 * 
 * @author Ash
 * @author rohitjadhav
 */
@RestController
@RequestMapping("/routes")
@CrossOrigin("*")
public class RouteController {

	@Autowired
	private IRouteService routeService;

	/**
	 * Get a list of all routes.
	 * 
	 * @return List of all routes
	 */
	@GetMapping("/getallroutes")
	public List<Route> getAllRoutes() {
		return routeService.getAllRoutes();
	}

	/**
	 * Get a route by its ID.
	 * 
	 * @param routeId The ID of the route
	 * @return ResponseEntity with appropriate status and response body
	 */
	@GetMapping("/getroutebyid/{routeId}")
	public ResponseEntity<?> getRouteById(@PathVariable("routeId") Integer routeId) {
		try {
			Route route = routeService.findRouteById(routeId);
			return ResponseEntity.ok(route);
		} catch (RouteNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}

	/**
	 * Create a new route.
	 * 
	 * @param route         The route to be created
	 * @param bindingResult BindingResult for validation
	 * @return ResponseEntity with appropriate status and response body
	 */
	@PostMapping("/createroute")
	public ResponseEntity<?> createRoute(@Valid @RequestBody Route route, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			String errorMessage = bindingResult.getFieldErrors().get(0).getDefaultMessage();
			return ResponseEntity.badRequest().body(errorMessage);
		}

		Route createdRoute = routeService.insertRoute(route);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRoute);
	}

	/**
	 * Update a route by its ID.
	 * 
	 * @param routeId      The ID of the route to be updated
	 * @param updatedRoute The updated route data
	 * @return ResponseEntity with appropriate status and response body
	 */
	@PutMapping("/updateroute/{routeId}")
	public ResponseEntity<?> updateRoute(@PathVariable("routeId") Integer routeId, @RequestBody Route updatedRoute) {
		try {
			Route updated = routeService.updateRoute(routeId, updatedRoute);
			return ResponseEntity.ok(updated);
		} catch (RouteNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}

	/**
	 * Delete a route by its ID.
	 * 
	 * @param routeId The ID of the route to be deleted
	 * @return ResponseEntity with appropriate status and response body
	 */
	@DeleteMapping("/deleteroute/{routeId}")
	public ResponseEntity<String> deleteRoute(@PathVariable("routeId") Integer routeId) {
		try {
			routeService.deleteRoute(routeId);
			return ResponseEntity.ok("Route deleted successfully");
		} catch (RouteNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
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
