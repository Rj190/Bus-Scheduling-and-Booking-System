package com.crimsonlogic.busschedulingandbookingsystem.service;

import java.util.List;
import java.util.Optional;

import com.crimsonlogic.busschedulingandbookingsystem.entity.BookingDetails;

 

public interface BookingDetailsService {
    List<BookingDetails> getAllBookingDetails();
    Optional<BookingDetails> getBookingDetailsById(Integer id);
    BookingDetails createBookingDetails(BookingDetails bookingDetails);
    BookingDetails updateBookingDetails(Integer id, BookingDetails bookingDetails);
    void deleteBookingDetails(Integer id);
    List<BookingDetails> getBookingDetailsByBookingId(Integer bookingId);
}