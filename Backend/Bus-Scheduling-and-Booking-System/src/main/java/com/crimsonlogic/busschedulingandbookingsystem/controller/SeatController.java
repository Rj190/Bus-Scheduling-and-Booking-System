package com.crimsonlogic.busschedulingandbookingsystem.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.crimsonlogic.busschedulingandbookingsystem.entity.Seat;
import com.crimsonlogic.busschedulingandbookingsystem.service.ISeatService;

@Controller
@RestController
@RequestMapping("/seat")
public class SeatController {
	@Autowired
	private ISeatService seatService;
	
	
	@GetMapping()
	@RequestMapping("/viewallseats")
	public List<Seat> viewAllSeats(){
return seatService.viewAllSeats();
}
	@PostMapping("/insertseat")   
    public Seat insertSeat(@RequestBody Seat seat) {
	return seatService.insertSeat(seat);
	}
	  @GetMapping("/getseatsbyid/{seatid}")
			public Seat  viewSeatById(@PathVariable("seatid")int seatId)  {
				return seatService.viewSeatById(seatId);
			}
	  @DeleteMapping("/deleteseat/{seatid}")
		public void deleteSeat(@PathVariable("seatid")int seatid) {
			seatService.deleteSeatById(seatid);
		}
	  @PutMapping("/updateseatsbyid/{seatid}")
	  	public Seat updateSeatById(@PathVariable("seatid")int seatId,
	  			   @RequestBody Seat newSeat)   {
	  		return seatService.updateSeatById(seatId,newSeat);
	  	}

}

