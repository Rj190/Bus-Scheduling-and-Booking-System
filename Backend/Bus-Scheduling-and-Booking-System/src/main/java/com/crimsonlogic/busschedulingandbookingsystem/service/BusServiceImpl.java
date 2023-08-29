package com.crimsonlogic.busschedulingandbookingsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Bus;
import com.crimsonlogic.busschedulingandbookingsystem.exception.ResourceNotFoundException;

@Service
public class BusServiceImpl  implements IBusService {

	@Override
	public List<Bus> getAllBuses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bus saveBus(Bus bus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bus updateBus(Bus bus) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Bus> getBusById(Integer busId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBusById(Integer busId) {
		// TODO Auto-generated method stub
		
	}

}
