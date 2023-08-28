package com.crimsonlogic.busschedulingandbookingsystem.service;

import java.util.List;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Seat;

public interface ISeatService {
	public List<Seat> viewAllSeats();
	public Seat viewSeatById(int seatId);
	public Seat insertSeat(Seat seat); 
	public void deleteSeatById(int seatId);
	public Seat updateSeatById(int seatId,Seat seat);
}
