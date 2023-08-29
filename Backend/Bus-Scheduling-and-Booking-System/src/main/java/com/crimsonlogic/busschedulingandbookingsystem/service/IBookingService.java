package com.crimsonlogic.busschedulingandbookingsystem.service;

import java.util.List;
import java.util.Optional;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Booking;
import com.crimsonlogic.busschedulingandbookingsystem.exception.ResourceNotFoundException;

public interface IBookingService {
	
	public List <Booking> getAllBookings();
	public Booking saveBooking(Booking booking);
	public Booking updateBooking(Booking booking) throws ResourceNotFoundException;
	public Optional<Booking> getBookingById(Integer bookingId);
	public void deleteBookingById(Integer bookingId);
}


