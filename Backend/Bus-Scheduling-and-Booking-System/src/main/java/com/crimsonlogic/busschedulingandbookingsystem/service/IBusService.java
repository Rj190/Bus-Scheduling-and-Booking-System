package com.crimsonlogic.busschedulingandbookingsystem.service;

import java.util.List;
import java.util.Optional;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Bus;
import com.crimsonlogic.busschedulingandbookingsystem.exception.ResourceNotFoundException;

public interface IBusService {
	
	public List <Bus> getAllBuses();
	public Bus saveBus(Bus bus);
	public Bus updateBus(Bus bus) throws ResourceNotFoundException;
	public Optional<Bus> getBusById(Integer busId);
	public void deleteBusById(Integer busId);
}
