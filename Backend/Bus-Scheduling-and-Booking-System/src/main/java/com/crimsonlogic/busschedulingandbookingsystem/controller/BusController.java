package com.crimsonlogic.busschedulingandbookingsystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Bus;
import com.crimsonlogic.busschedulingandbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.service.IBusService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin("*")
@RestController
@RequestMapping("/bus")
public class BusController {
	
	@Autowired
	private IBusService busService;

	@GetMapping("/listbuses")
	public List<Bus> getAllBuses() {
		return busService.getAllBuses();
	}

	@PostMapping("/savebus")
	public Bus saveBus(Bus bus) {
		return busService.saveBus(bus);
	}

	@PutMapping("/updatebus")
	public Bus updateBus(@RequestBody Bus bus, @PathVariable Integer busId)
			throws ResourceNotFoundException {
		Bus bus1 =  busService.getBusById(busId)
				.orElseThrow(() -> new ResourceNotFoundException("bus not exists with id" +busId));
		
		Bus newBus = null;
		bus1.setBusType(newBus.getBusType());
		bus1.setBusCapacity(newBus.getBusCapacity());
		bus1.setBusRegistrationNumber(newBus.getBusRegistrationNumber());
		bus1.setBusAvailabilityStatus(newBus.getBusAvailabilityStatus());
		return busService.updateBus(bus);
	}

	@GetMapping("/findbus")
	public Bus getBusById(@PathVariable Integer busId) {
		return busService.getBusById(busId).get();
		}

	@DeleteMapping("/deletebus")
	public void deleteBusById(@PathVariable Integer busId) {
		busService.deleteBusById(busId);
		
	}

}

