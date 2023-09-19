package com.crimsonlogic.busschedulingandbookingsystem.service;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Booking;
import com.crimsonlogic.busschedulingandbookingsystem.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.busschedulingandbookingsystem.entity.BookingDetails;
import com.crimsonlogic.busschedulingandbookingsystem.repository.BookingDetailsRepository;

import java.util.List;
import java.util.Optional;

 

@Service
public class BookingDetailsServiceImpl implements BookingDetailsService {

 

    @Autowired
    private BookingDetailsRepository bookingDetailsRepository;

    @Autowired
    private BookingRepository bookingRepository;

 

    @Override
    public List<BookingDetails> getAllBookingDetails() {
        return bookingDetailsRepository.findAll();
    }

 

    @Override
    public Optional<BookingDetails> getBookingDetailsById(Integer id) {
        return bookingDetailsRepository.findById(id);
    }

 

    @Override
    public BookingDetails createBookingDetails(BookingDetails bookingDetails) {
        return bookingDetailsRepository.save(bookingDetails);
    }

 

    @Override
    public BookingDetails updateBookingDetails(Integer id, BookingDetails bookingDetails) {
        if (bookingDetailsRepository.existsById(id)) {
            bookingDetails.setBookingDetailsId(id);
            return bookingDetailsRepository.save(bookingDetails);
        }
        return null; // Handle the case where booking details with the given ID doesn't exist
    }

 

    @Override
    public void deleteBookingDetails(Integer id) {
        bookingDetailsRepository.deleteById(id);
    }

    @Override
    public List<BookingDetails>  getBookingDetailsByBookingId(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId).get();
        return bookingDetailsRepository.findAllByBooking(booking);
    }


}