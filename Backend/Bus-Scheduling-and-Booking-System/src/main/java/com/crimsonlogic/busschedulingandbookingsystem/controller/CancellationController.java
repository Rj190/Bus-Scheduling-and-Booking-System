package com.crimsonlogic.busschedulingandbookingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Cancellation;
import com.crimsonlogic.busschedulingandbookingsystem.service.ICancellationService;

@RestController
@RequestMapping("/api/cancellation")
public class CancellationController {

	@Autowired
	private ICancellationService cancellationService;

	@GetMapping("/cancellation-list")
	public List<Cancellation> getAllCancellation(){
		return cancellationService.getAllCancellation();
		
	}

	
	@PostMapping("/create")
	public Cancellation createCancellation(@RequestBody Cancellation cancellation) {
		return cancellationService.createCancellation(cancellation);
		
	}

	@PutMapping("/update/{id}")
	public Cancellation updateCancellation(@PathVariable("id") Integer cancellationID,@RequestBody Cancellation cancellation) {
		return cancellationService.updateCancellation(cancellationID, cancellation);
	}


	@DeleteMapping("/delete")
	public void deleteCancellation(Integer cancellationID) {
		cancellationService.deleteCancellation(cancellationID);
		
	}

	@GetMapping("/{id}")
	public Cancellation getCancellationById(@PathVariable("id") Integer cancellationID) {
		return cancellationService.getCancellationById(cancellationID);
		
	}

}
