package com.crimsonlogic.busschedulingandbookingsystem.repository;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.busschedulingandbookingsystem.entity.BookingDetails;

import java.util.List;

@Repository
public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Integer> {
    List<BookingDetails> findAllByBooking(Booking booking);

}