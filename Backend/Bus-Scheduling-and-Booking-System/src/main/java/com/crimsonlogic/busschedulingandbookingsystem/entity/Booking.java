package com.crimsonlogic.busschedulingandbookingsystem.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "booking_data")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Booking {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingId;
	
	private LocalDate bookingDate;
	
	private String bookingStatus;
	
	private int bookingTotalFare;
	
	private int bookingNumPassengers;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "userId_fk")
	@JsonIgnoreProperties("bookings")
	private User user;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "journeyId_fk")
	@JsonIgnoreProperties("bookings")
	private Journey journey;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "paymentId_fk")
	@JsonIgnoreProperties("bookings")
	private Payment payment;

	@JsonIgnore
	@OneToMany(mappedBy = "booking")
	@JsonIgnoreProperties("booking")
	private List<BookingDetails> bookingDetails;



}
