package com.crimsonlogic.busschedulingandbookingsystem.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "seat_info")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer seatId;

	@NotBlank(message = "Seat number is required")
	@Size(max = 3, message = "Seat number can have at most 10 characters")
	private String seatNumber;

	private String seatAvailabilityStatus;

	@ManyToOne
	@JoinColumn(name = "busId_fk", nullable = false)
	@JsonIgnoreProperties("seats")
	private Bus bus;
	
	@OneToOne(mappedBy = "seat")
	@JsonIgnoreProperties("seat")
	private BookingDetails bookingDetails;

}
