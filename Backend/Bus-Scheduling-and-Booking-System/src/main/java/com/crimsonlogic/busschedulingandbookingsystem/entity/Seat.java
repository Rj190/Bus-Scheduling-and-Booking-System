package com.crimsonlogic.busschedulingandbookingsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="seat_info")
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer seatId;
	@Column(name="seatnumber")
	 private String seatNumber;
	@Column(name="seatavailabilitystatus")
	 private String seatAvailabilityStatus;
	public Seat() {
		super();
	}
	public Seat(Integer seatId, String seatNumber, String seatAvailabilityStatus) {
		super();
		this.seatId = seatId;
		this.seatNumber = seatNumber;
		this.seatAvailabilityStatus = seatAvailabilityStatus;
	}
	
	public Integer getSeatId() {
		return seatId;
	}
	public void setSeatId(Integer seatId) {
		this.seatId = seatId;
	}
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	public String getSeatAvailabilityStatus() {
		return seatAvailabilityStatus;
	}
	public void setSeatAvailabilityStatus(String seatAvailabilityStatus) {
		this.seatAvailabilityStatus = seatAvailabilityStatus;
	}
	@Override
	public String toString() {
		return "Seat [seatId=" + seatId + ", seatNumber=" + seatNumber + ", seatAvailabilityStatus="
				+ seatAvailabilityStatus + "]";
	}
}
