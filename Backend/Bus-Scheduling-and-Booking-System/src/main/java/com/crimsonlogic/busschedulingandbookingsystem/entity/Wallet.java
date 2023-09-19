package com.crimsonlogic.busschedulingandbookingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="wallet_info")
public class Wallet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer walletId;
	
	@Column(name="walletbalance")
	@NotNull
	private double walletBalance;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userID_fk")
	@JsonIgnoreProperties("wallet")
	private User user;


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	//default constructor
	public Wallet() {
		super();
	}
	
	
	//Parameterized constructors
	public Wallet(Integer walletId, @NotNull double walletBalance) {
		super();
		this.walletId = walletId;
		this.walletBalance = walletBalance;
	}


	//getters and setters
	public Integer getWalletId() {
		return walletId;
	}


	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}


	public double getWalletBalance() {
		return walletBalance;
	}


	public void setWalletBalance(double walletBalance) {
		this.walletBalance = walletBalance;
	}
	
	//to String()
	@Override
	public String toString() {
		return "Wallet [walletId=" + walletId + ", walletBalance=" + walletBalance + "]";
	}
		
	
}
