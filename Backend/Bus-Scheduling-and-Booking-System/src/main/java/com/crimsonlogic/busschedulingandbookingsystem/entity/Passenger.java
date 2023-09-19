package com.crimsonlogic.busschedulingandbookingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="passenger_info")
public class Passenger {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer passengerId;


	    private String passengerName;
	    
	    private String passengerLastName;
	    

	    private int passengerAge;
	    
	    private String passengerGender;

	    private String passengerMobileNo;
	    
	   
	    private String passengerEmail;
	    
	    
	    @OneToOne(mappedBy = "passenger")
		@JsonIgnoreProperties("passenger")
		private BookingDetails bookingDetails;

	  
	    //default constructors
		public Passenger() {
			super();
		}
		
		

		//Parameterized constructors
		public Passenger(Integer passengerId, String passengerName,
				String passengerLastName, int passengerAge, String passengerGender, String passengerMobileNo,
				String passengerEmail) {
			super();
			this.passengerId = passengerId;
			this.passengerName = passengerName;
			this.passengerLastName = passengerLastName;
			this.passengerAge = passengerAge;
			this.passengerGender = passengerGender;
			this.passengerMobileNo = passengerMobileNo;
			this.passengerEmail = passengerEmail;
		}

		
		

		public Passenger(Integer passengerId, String passengerName, String passengerLastName, int passengerAge,
				String passengerGender, String passengerMobileNo, String passengerEmail,
				BookingDetails bookingDetails) {
			super();
			this.passengerId = passengerId;
			this.passengerName = passengerName;
			this.passengerLastName = passengerLastName;
			this.passengerAge = passengerAge;
			this.passengerGender = passengerGender;
			this.passengerMobileNo = passengerMobileNo;
			this.passengerEmail = passengerEmail;
			this.bookingDetails = bookingDetails;
		}



		//getters and setters
		public Integer getPassengerId() {
			return passengerId;
		}

		public void setPassengerId(Integer passengerId) {
			this.passengerId = passengerId;
		}
		public String getPassengerName() {
			return passengerName;
		}

		public void setPassengerName(String passengerName) {
			this.passengerName = passengerName;
		}

		public String getPassengerLastName() {
			return passengerLastName;
		}

		public void setPassengerLastName(String passengerLastName) {
			this.passengerLastName = passengerLastName;
		}

		public int getPassengerAge() {
			return passengerAge;
		}

		public void setPassengerAge(int passengerAge) {
			this.passengerAge = passengerAge;
		}

		public String getPassengerGender() {
			return passengerGender;
		}

		public void setPassengerGender(String passengerGender) {
			this.passengerGender = passengerGender;
		}

		public String getPassengerMobileNo() {
			return passengerMobileNo;
		}

		public void setPassengerMobileNo(String passengerMobileNo) {
			this.passengerMobileNo = passengerMobileNo;
		}

		public String getPassengerEmail() {
			return passengerEmail;
		}

		public void setPassengerEmail(String passengerEmail) {
			this.passengerEmail = passengerEmail;
		}

		
		public BookingDetails getBookingDetails() {
			return bookingDetails;
		}



		public void setBookingDetails(BookingDetails bookingDetails) {
			this.bookingDetails = bookingDetails;
		}



		//to String()
		@Override
		public String toString() {
			return "Passenger [passengerId=" + passengerId + ", passengerName="
					+ passengerName + ", passengerLastName=" + passengerLastName + ", passengerAge=" + passengerAge
					+ ", passengerGender=" + passengerGender + ", passengerMobileNo=" + passengerMobileNo
					+ ", passengerEmail=" + passengerEmail + "]";
		}   
		
		
		
	    
	}

