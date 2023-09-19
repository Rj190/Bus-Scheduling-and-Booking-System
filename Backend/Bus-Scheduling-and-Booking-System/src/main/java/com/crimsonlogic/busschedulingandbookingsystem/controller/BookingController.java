package com.crimsonlogic.busschedulingandbookingsystem.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.crimsonlogic.busschedulingandbookingsystem.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.crimsonlogic.busschedulingandbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.service.IBookingService;
import com.crimsonlogic.busschedulingandbookingsystem.service.IJourneyService;
import com.crimsonlogic.busschedulingandbookingsystem.service.IPaymentService;
import com.crimsonlogic.busschedulingandbookingsystem.service.IUserService;



@CrossOrigin("*")
@RestController
@RequestMapping("api/booking")
public class BookingController {

	@Autowired
	private IBookingService bookingService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IPaymentService paymentService;

	@Autowired
	private IJourneyService journeyServic;

	@GetMapping("/all")
	public List<Booking> getAllBookings() {
		return bookingService.getAllBookings();
	}

	@PostMapping("/add/{username}/{journeyId}/{paymentId}")
	public ResponseEntity<?> saveBooking(@PathVariable("username") String UserName,
										 @PathVariable("journeyId") Integer journeyId, @PathVariable("paymentId") Integer paymentId,
										 @Valid @RequestBody Booking booking, BindingResult bindingResult) {

		try {


			if (bindingResult.hasErrors()) {
				// Handle validation errors
				StringBuilder errorMessage = new StringBuilder();
				for (FieldError fieldError : bindingResult.getFieldErrors()) {
					errorMessage.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage())
							.append("; ");
				}
				return ResponseEntity.badRequest().body(errorMessage.toString());
			}


			User user = userService.findByUsername(UserName)
					.orElseThrow(() -> new ResourceNotFoundException("User", "UserName", UserName));
			Journey journey = journeyServic.viewJourneyById(journeyId);
			Payment payment = paymentService.viewPaymentById(paymentId)
					.orElseThrow(() -> new ResourceNotFoundException("Payment", "Payment ID", paymentId));

			booking.setUser(user);
			booking.setJourney(journey);
			booking.setPayment(payment);

			Booking createdBooking = bookingService.saveBooking(booking);


			return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@PutMapping("/updatebooking")
	public Booking updateBooking(@RequestBody Booking booking, @PathVariable Integer bookingId)
			throws ResourceNotFoundException {
		bookingService.getBookingById(bookingId)
				.orElseThrow(() -> new ResourceNotFoundException("Booking", "BookingID", bookingId));
		return bookingService.saveBooking(booking);
	}

	@GetMapping("/findbooking")
	public Optional<Booking> getBookingById(@PathVariable Integer bookingId) {
		return Optional.of(bookingService.getBookingById(bookingId).get());

	}

	@DeleteMapping("/deletebooking")
	public void deleteBookingById(@PathVariable Integer bookingId) {
		bookingService.deleteBookingById(bookingId);
	}




}
