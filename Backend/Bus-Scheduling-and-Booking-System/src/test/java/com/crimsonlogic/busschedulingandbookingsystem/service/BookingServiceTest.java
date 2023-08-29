package com.crimsonlogic.busschedulingandbookingsystem.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Booking;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private IBookingService bookingService;

    @Test
    public void testCreateBooking() {
        Journey journey = new Journey();
        journey.setId(1L);
        
        Booking booking = new Booking();
        booking.setJourney(journey);
        booking.setNumberOfSeats(3);

        when(bookingRepository.save(any())).thenReturn(booking);

        Booking createdBooking = bookingService.createBooking(booking);

        assertNotNull(createdBooking);
        assertEquals(journey, createdBooking.getJourney());
        assertEquals(3, createdBooking.getNumberOfSeats());
    }

    @Test
    public void testGetBookingById() {
        Booking booking = new Booking();
        booking.setId(1L);
        Journey journey = new Journey();
        journey.setId(1L);
        booking.setJourney(journey);
        booking.setNumberOfSeats(2);

        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));

        Booking retrievedBooking = bookingService.getBookingById(1L);

        assertNotNull(retrievedBooking);
        assertEquals(journey, retrievedBooking.getJourney());
        assertEquals(2, retrievedBooking.getNumberOfSeats());
    }

    @Test
    public void testCancelBooking() {
        Booking booking = new Booking();
        booking.setId(1L);
        Journey journey = new Journey();
        journey.setId(1L);
        booking.setJourney(journey);
        booking.setNumberOfSeats(2);

        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));

        bookingService.cancelBooking(1L);

        verify(bookingRepository, times(1)).delete(booking);
    }

}
