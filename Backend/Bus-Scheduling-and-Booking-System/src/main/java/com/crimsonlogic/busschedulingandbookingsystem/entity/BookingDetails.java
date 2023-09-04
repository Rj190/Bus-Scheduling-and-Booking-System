package com.crimsonlogic.busschedulingandbookingsystem.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bookingdetails_info")
@AllArgsConstructor
@Setter
@Getter
@Data
public class BookingDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookingDetailsId;
	
	@ManyToOne
	@JoinColumn(name = "bookingId_fk")
	@JsonIgnoreProperties("bookingDetails")
	private Booking booking;
	
	@OneToOne
	@JoinColumn(name = "seatId_fk")
	//@JsonIgnoreProperties("bookingDetails")
	private Seat seat;
	
	@OneToOne
	@JoinColumn(name = "passengerId_fk")
	//@JsonIgnoreProperties("bookingDetails")
	private Passenger passenger;

}
