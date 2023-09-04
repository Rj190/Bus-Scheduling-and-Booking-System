package com.crimsonlogic.busschedulingandbookingsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Bus;
import com.crimsonlogic.busschedulingandbookingsystem.entity.Seat;
import com.crimsonlogic.busschedulingandbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.repository.BusRepository;
import com.crimsonlogic.busschedulingandbookingsystem.repository.ISeatRepository;

/**
 * Service implementation for managing buses.
 * 
 * @author Karthik Jadhav
 * @author Rohit jadhav
 */
@Service
@Transactional
public class BusServiceImpl implements IBusService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private ISeatRepository seatRepository;

    /**
     * Retrieve a list of all buses.
     * 
     * @return List of all buses
     */
    @Override
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    /**
     * Save a new bus along with its seats.
     * 
     * @param bus The bus to be saved
     * @return The saved bus entity
     */
    @Override
    public Bus saveBus(Bus bus) {
        Bus savedBus = busRepository.save(bus);

        for (Seat seat : bus.getSeats()) {
            seat.setBus(savedBus);
            seatRepository.save(seat);
        }

        List<Seat> seats = seatRepository.findAllByBus(savedBus);
        savedBus.setSeats(seats);

        return savedBus;
    }

    /**
     * Update an existing bus along with its seats.
     * 
     * @param bus    The updated bus information
     * @param busId  The ID of the bus to be updated
     * @return The updated bus entity
     */
    @Override
    public Bus updateBus(Bus bus, Integer busId) {
        Bus existingBus = busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus", "Bus Id", busId));

        existingBus.setBusType(bus.getBusType());
        existingBus.setBusCapacity(bus.getBusCapacity());
        existingBus.setBusRegistrationNumber(bus.getBusRegistrationNumber());
        existingBus.setBusAvailabilityStatus(bus.getBusAvailabilityStatus());

        Bus updatedBus = busRepository.save(existingBus);

        for (Seat seat : bus.getSeats()) {
            if (seat.getSeatId() != null) {
                Seat existingSeat = seatRepository.findById(seat.getSeatId())
                        .orElseThrow(() -> new ResourceNotFoundException("Seat", "Seat Id", seat.getSeatId()));
                existingSeat.setSeatAvailabilityStatus(seat.getSeatAvailabilityStatus());
                seatRepository.save(existingSeat);
            } else {
                seat.setBus(updatedBus);
                seatRepository.save(seat);
            }
        }
        return updatedBus;
    }

    /**
     * Retrieve a bus by its ID.
     * 
     * @param busId The ID of the bus
     * @return The optional bus entity
     */
    @Override
    public Optional<Bus> getBusById(Integer busId) {
        return busRepository.findById(busId);
    }

    /**
     * Delete a bus by its ID along with its associated seats.
     * 
     * @param busId The ID of the bus to be deleted
     * @return Deletion status message
     * @throws ResourceNotFoundException If the bus with the specified ID is not found
     */
    @Override
    public String deleteBusById(Integer busId) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus", "Bus Id", busId));

        for (Seat seat : bus.getSeats()) {
            seatRepository.deleteById(seat.getSeatId());
        }

        busRepository.deleteById(busId);

        if (!busRepository.existsById(busId)) {
            return "Bus and associated seats have been successfully deleted.";
        } else {
            throw new RuntimeException("Deletion was not successful.");
        }
    }
}
