package com.crimsonlogic.busschedulingandbookingsystem.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="paymentdetails")
public class Payment {
	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="paymentid")
	private Integer paymentId;
	private LocalDate paymentDate;
	private double paymentAmount;
	

}
