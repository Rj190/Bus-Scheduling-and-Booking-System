package com.crimsonlogic.busschedulingandbookingsystem.service;

import java.util.List;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Passenger;

public interface IPassengerService {

	List<Passenger> getAllPassengers();
	Passenger getPassengerById(Integer id);
	Passenger createPassenger(Passenger passenger);
	Passenger updatePassengerbyId(Integer passengerId, Passenger passenger);
	void deletePassengerbyId(Integer id);

}
