package com.crimsonlogic.busschedulingandbookingsystem.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "bus_data")
@ToString
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer busId;

    @NotBlank(message = "Bus name is required")
    @Size(min = 2, max = 20, message = "Bus name must be between 2 and 20 characters")
    private String busName;

    @NotBlank(message = "Bus type is required")
    private String busType;

    @Min(value = 20, message = "Bus capacity must be at least 20")
    private int busCapacity;

    @NotBlank(message = "Bus registration number is required")
    @Size(max = 20, message = "Bus registration number can have at most 20 characters")
    private String busRegistrationNumber;

    private String busAvailabilityStatus;

    @OneToMany(mappedBy = "bus")
    @JsonIgnoreProperties("bus")
    private List<Seat> seats;
    
    @OneToMany(mappedBy = "bus")
    @JsonIgnoreProperties("bus")
    private List<Journey> journeys ;
    

	public Bus() {
	
	}
	
	


	public Bus(int busId, String busName, String busType, int busCapacity, String busRegistrationNumber,
			String busAvailabilityStatus) {
		super();
		this.busId = busId;
		this.busName = busName;
		this.busType = busType;
		this.busCapacity = busCapacity;
		this.busRegistrationNumber = busRegistrationNumber;
		this.busAvailabilityStatus = busAvailabilityStatus;
	}

	
	



	public Bus(int busId, String busName, String busType, int busCapacity, String busRegistrationNumber,
			String busAvailabilityStatus, List<Seat> seats) {
		super();
		this.busId = busId;
		this.busName = busName;
		this.busType = busType;
		this.busCapacity = busCapacity;
		this.busRegistrationNumber = busRegistrationNumber;
		this.busAvailabilityStatus = busAvailabilityStatus;
		this.seats = seats;
	}




	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public int getBusCapacity() {
		return busCapacity;
	}

	public void setBusCapacity(int busCapacity) {
		this.busCapacity = busCapacity;
	}

	public String getBusRegistrationNumber() {
		return busRegistrationNumber;
	}

	public void setBusRegistrationNumber(String busRegistrationNumber) {
		this.busRegistrationNumber = busRegistrationNumber;
	}

	public String getBusAvailabilityStatus() {
		return busAvailabilityStatus;
	}

	public void setBusAvailabilityStatus(String busAvailabilityStatus) {
		this.busAvailabilityStatus = busAvailabilityStatus;
	}
	

	public List<Seat> getSeats() {
		return seats;
	}



	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}




	public List<Journey> getJourneys() {
		return journeys;
	}




	public void setJourneys(List<Journey> journeys) {
		this.journeys = journeys;
	}




	public void setBusId(Integer busId) {
		this.busId = busId;
	}


	
	


	
}
