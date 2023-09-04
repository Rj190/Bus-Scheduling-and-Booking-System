package com.crimsonlogic.busschedulingandbookingsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Booking;
import com.crimsonlogic.busschedulingandbookingsystem.exception.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;

 

import org.springframework.beans.factory.annotation.Autowired;


import com.crimsonlogic.busschedulingandbookingsystem.entity.Booking;
import com.crimsonlogic.busschedulingandbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.repository.BookingRepository;

 

@Service
public class BookingServiceImpl implements IBookingService {

	@Autowired
    private BookingRepository bookRepo;

	@Override
	public List<Booking> getAllBookings() {
		return bookRepo.findAll();
	}

 

	@Override
	public Booking saveBooking(Booking booking) {
		if(booking.getPayment().getPayementStatus().equalsIgnoreCase("failed")) {
			throw new RuntimeException("Payment failed");
		}
		return bookRepo.save(booking);
	}

 

	@Override
	public Booking updateBooking(Booking booking) throws ResourceNotFoundException {
		return bookRepo.save(booking);
	}

 

	@Override
	public Optional<Booking> getBookingById(Integer bookingId) {
		return bookRepo.findById(bookingId);
	}

 

	@Override
	public void deleteBookingById(Integer bookingId) {
		bookRepo.deleteById(bookingId);
	}

 

}
