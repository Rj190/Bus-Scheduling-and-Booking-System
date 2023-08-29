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

import com.crimsonlogic.busschedulingandbookingsystem.entity.Booking;
import com.crimsonlogic.busschedulingandbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.service.IBookingService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin("*")
@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private IBookingService bookingService;
	
	@GetMapping("/listbooking")
	public List<Booking> getAllBookings() {
		return bookingService.getAllBookings();
	}

	@PostMapping("/savebooking")
	public Booking saveBooking(Booking booking) {
		return bookingService.saveBooking(booking);
	}

	@SuppressWarnings("null")
	@PutMapping("/updatebooking")
	public Booking updateBooking(@RequestBody Booking booking, @PathVariable Integer bookingId)
			throws ResourceNotFoundException {
		Booking booking1 =  bookingService.getBookingById(bookingId)
				.orElseThrow(() -> new ResourceNotFoundException("Booking", "BookingID",bookingId));
		
		Booking newBooking = null;
		booking.setBookingUserId(newBooking.getBookingUserId());
		booking.setBookingJourneyId(newBooking.getBookingJourneyId());
		booking.setBookingDate(newBooking.getBookingDate());
		booking.setBookingStatus(newBooking.getBookingStatus());
		booking.setBookingTotalFare(newBooking.getBookingTotalFare());
		booking.setBookingnumpassengers(newBooking.getBookingnumpassengers());
		booking.setBookingPaymentId(newBooking.getBookingPaymentId());
		return bookingService.saveBooking(booking);	}

	@GetMapping("/findbooking")
	public Optional<Booking> getBookingById(@PathVariable Integer bookingId) {
		return Optional.of(bookingService.getBookingById(bookingId).get());

	}

	@DeleteMapping("/deletebooking")
	public void deleteBookingById(@PathVariable Integer bookingId) {
		bookingService.deleteBookingById(bookingId);		
	}
	
}
