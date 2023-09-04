package com.crimsonlogic.busschedulingandbookingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Booking;
import com.crimsonlogic.busschedulingandbookingsystem.entity.BookingDetails;
import com.crimsonlogic.busschedulingandbookingsystem.entity.Passenger;
import com.crimsonlogic.busschedulingandbookingsystem.entity.Seat;
import com.crimsonlogic.busschedulingandbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.service.BookingDetailsService;
import com.crimsonlogic.busschedulingandbookingsystem.service.IBookingService;
import com.crimsonlogic.busschedulingandbookingsystem.service.IPassengerService;

import java.util.List;

@RestController
@RequestMapping("api/booking-details")
public class BookingDetailsController {

	@Autowired
	private BookingDetailsService bookingDetailsService;

	@Autowired
	private IBookingService bookingService;

	@Autowired
	private IPassengerService passengerService;

	@GetMapping
	public ResponseEntity<?> getAllBookingDetails() {
		List<BookingDetails> bookingDetails = bookingDetailsService.getAllBookingDetails();
		return ResponseEntity.ok(bookingDetails);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BookingDetails> getBookingDetailsById(@PathVariable Integer id) {
		return bookingDetailsService.getBookingDetailsById(id).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/{bookingId}/{seatId}/{passengerId}")
	public ResponseEntity<?> createBookingDetails(@PathVariable("bookingId") Integer bookingId,
			@PathVariable("seatId") Integer seatId, @PathVariable("passengerId") Integer passengerId,
			@RequestBody BookingDetails bookingDetails) {

		try {

			Booking booking = bookingService.getBookingById(bookingId)
					.orElseThrow(() -> new ResourceNotFoundException("Booking", "Booking ID", bookingId));
			List<Seat> seats = booking.getJourney().getBus().getSeats();
			Seat seat = (Seat) seats.stream().map(exSeat -> exSeat.getSeatId().compareTo(seatId));
			Passenger passenger = passengerService.getPassengerById(passengerId);
			if (seat != null) {
				bookingDetails.setBooking(booking);
				bookingDetails.setSeat(seat);
				bookingDetails.setPassenger(passenger);
				BookingDetails createdBookingDetails = bookingDetailsService.createBookingDetails(bookingDetails);
				return ResponseEntity.status(HttpStatus.CREATED).body(createdBookingDetails);

			} else {
				throw new ResourceNotFoundException("Seat", "Seat ID", seatId);
			}

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<BookingDetails> updateBookingDetails(@PathVariable Integer id,
			@RequestBody BookingDetails bookingDetails) {
		BookingDetails updatedBookingDetails = bookingDetailsService.updateBookingDetails(id, bookingDetails);
		if (updatedBookingDetails != null) {
			return ResponseEntity.ok(updatedBookingDetails);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBookingDetails(@PathVariable Integer id) {
		bookingDetailsService.deleteBookingDetails(id);
		return ResponseEntity.noContent().build();
	}
}