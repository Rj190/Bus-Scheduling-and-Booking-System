package com.crimsonlogic.busschedulingandbookingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Bus;
import com.crimsonlogic.busschedulingandbookingsystem.entity.Seat;
import com.crimsonlogic.busschedulingandbookingsystem.repository.ISeatRepository;
@Service
public class SeatServiceImpl implements ISeatService {
	@Autowired                                
    private ISeatRepository seatRepo;

	@Override
	public List<Seat> viewAllSeats() {
		return seatRepo.findAll();
	}

	@Override
	public Seat viewSeatById(int seatId) {
		return seatRepo.findById(seatId).get();
	}

	

	@Override
	public void deleteSeatById(int seatId) {
		seatRepo.deleteById(seatId);
		
	}
	//to update the seat no

	@Override
	public Seat updateSeatById(int seatId, Seat newseat) {

			  Seat existingSeat=viewSeatById(seatId);  
			  if(existingSeat != null) {
				  existingSeat.setSeatNumber(newseat.getSeatNumber());
				  existingSeat.setSeatAvailabilityStatus(newseat.getSeatAvailabilityStatus());
				  
			  }else {
				  System.out.println("SeatId not found");
			  }
			  return existingSeat; 

	}

	@Override
	public Seat insertSeat(Seat seat) {
		
		 return seatRepo.save(seat);
	}

	@Override
	public List<Seat> getSeatsByBus(Bus bus) {
		return seatRepo.findAllByBus(bus);
	}
}
			  
		 
