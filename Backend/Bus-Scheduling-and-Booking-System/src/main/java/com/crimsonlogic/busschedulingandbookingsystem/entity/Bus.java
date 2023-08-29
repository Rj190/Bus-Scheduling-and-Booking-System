package com.crimsonlogic.busschedulingandbookingsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bus_data")
public class Bus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bus_id")
	private int busId;
	
	@Column(name = "bus_type")
	private String busType;
	
	@Column(name = "bus_capacity")
	private int busCapacity;
	
	@Column(name = "bus_registrationnumber")
	private String busRegistrationNumber;
	
	@Column(name = "bus_availabilitystatus")
	private String busAvailabilityStatus;

	public Bus() {
		super();
	}

	public Bus(int busId, String busType, int busCapacity, String busRegistrationNumber, String busAvailabilityStatus) {
		super();
		this.busId = busId;
		this.busType = busType;
		this.busCapacity = busCapacity;
		this.busRegistrationNumber = busRegistrationNumber;
		this.busAvailabilityStatus = busAvailabilityStatus;
	}

	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
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

	public int getAvailableSeats1() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setAvailableSeats(int i) {
		// TODO Auto-generated method stub
		
	}

	public int getAvailableSeats() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
