package com.crimsonlogic.busschedulingandbookingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Passenger;
import com.crimsonlogic.busschedulingandbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.repository.IPassengerRepository;


@Service
public class PassengerServiceImpl implements IPassengerService{
    @Autowired
    private IPassengerRepository passRepo;
    
    @Override
    public List<Passenger> getAllPassengers() {
        return passRepo.findAll();
    }
    
    @Override
    public Passenger getPassengerById(Integer id) {
        return passRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Passenger", "Passenger Id", id));
    }
    
    @Override
    public Passenger createPassenger(Passenger passenger) {
        return passRepo.save(passenger);
    }
   
    @Override
    public Passenger updatePassengerbyId(Integer passengerId, Passenger passenger) {
        getPassengerById(passengerId);
        passenger.setPassengerId(passengerId);
        return passRepo.save(passenger);
    }
    
    @Override
    public void deletePassengerbyId(Integer id) {
    	passRepo.deleteById(id);
    }
}
