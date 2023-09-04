package com.crimsonlogic.busschedulingandbookingsystem.controller;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Seat;
import com.crimsonlogic.busschedulingandbookingsystem.entity.Bus;
import com.crimsonlogic.busschedulingandbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.exception.RouteNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.service.ISeatService;
import com.crimsonlogic.busschedulingandbookingsystem.service.IBusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/seat")
public class SeatController {

    @Autowired
    private ISeatService seatService;

    @Autowired
    private IBusService busService;

    @GetMapping("/viewallseats")
    public List<Seat> viewAllSeats() {
        return seatService.viewAllSeats();
    }

    @PostMapping("/insertseat/{busid}")
    public ResponseEntity<?> insertSeat(@PathVariable("busid") Integer busId, @Valid @RequestBody Seat seat,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Handle validation errors
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errorMessage.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage()).append("; ");
            }
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }

        Bus bus = busService.getBusById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus", "Bus Id", busId));

        List<Seat> seats = seatService.getSeatsByBus(bus);
        if (!seats.isEmpty()) {
            return ResponseEntity.badRequest().body("Bus seats already exist.");
        }

        Seat savedSeat = seatService.insertSeat(seat);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSeat);
    }

    @GetMapping("/getseatsbyid/{seatid}")
    public ResponseEntity<?> viewSeatById(@PathVariable("seatid") int seatId) {
        try {
            Seat foundSeat = seatService.viewSeatById(seatId);
            return ResponseEntity.ok(foundSeat);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteseat/{seatid}")
    public ResponseEntity<?> deleteSeat(@PathVariable("seatid") int seatId) {
        try {
            seatService.deleteSeatById(seatId);
            return ResponseEntity.ok("Seat deleted successfully.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateseatsbyid/{seatid}")
    public ResponseEntity<?> updateSeatById(@PathVariable("seatid") int seatId, @Valid @RequestBody Seat newSeat,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Handle validation errors
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errorMessage.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage()).append("; ");
            }
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }

        try {
            Seat updatedSeat = seatService.updateSeatById(seatId, newSeat);
            return ResponseEntity.ok(updatedSeat);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    // Handle custom exceptions using @ExceptionHandler
    @ExceptionHandler(RouteNotFoundException.class)
    public ResponseEntity<String> handleRouteNotFoundException(RouteNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }



    // Handle other exceptions if needed
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
    }
}
