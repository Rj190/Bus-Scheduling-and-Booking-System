package com.crimsonlogic.busschedulingandbookingsystem.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="payment_info")
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="paymentid")
	private Integer paymentId;
	private LocalDate paymentDate;
	private double paymentAmount;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "walletId_fk")
	private Wallet wallet;

}
