package com.crimsonlogic.busschedulingandbookingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Passenger;
import com.crimsonlogic.busschedulingandbookingsystem.service.IPassengerService;


@CrossOrigin("*")
@RestController
@RequestMapping("/passengers")
public class PassengerController {
    @Autowired
    private IPassengerService passServ;
    
    @GetMapping
    public List<Passenger> getAllPassengers() {
        return passServ.getAllPassengers();
    }
    
    @GetMapping("/{getpassengerbyid}/{id}")
    public Passenger getPassengerById(@PathVariable ("id") int id) {
        return passServ.getPassengerById(id);
    }
    
    @PostMapping("/createpassenger")
    public Passenger createPassenger(@RequestBody Passenger passenger) {
        return passServ.createPassenger(passenger);
    }
    
    @PutMapping("/{updatepassenegerbyid}/{id}")
    public Passenger updatePasseneger(@PathVariable ("id")  int id, @RequestBody Passenger passenger) {
        return passServ.updatePassengerbyId(id, passenger);
    }
    
    @DeleteMapping("/{deletepassengerbyid}/{id}")
    public void deletePassenger(@PathVariable ("id") int id) {
    	passServ.deletePassengerbyId(id);
    }
}

