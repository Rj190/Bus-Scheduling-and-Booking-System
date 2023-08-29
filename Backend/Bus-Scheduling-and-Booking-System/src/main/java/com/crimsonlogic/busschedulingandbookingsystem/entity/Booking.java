package com.crimsonlogic.busschedulingandbookingsystem.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "booking_data")
public class Booking {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private int bookingId;
	
	@Column(name = "bookinguser_id")
	private int bookingUserId;
	
	@Column(name = "booking_journeyid")
	private int bookingJourneyId;
	
	@Column(name = "booking_date")
	private Date bookingDate;
	
	@Column(name = "booking_status")
	private String bookingStatus;
	
	@Column(name = "booking_TotalFare")
	private int bookingTotalFare;
	
	@Column(name = "booking_numpassengers")
	private int bookingnumpassengers;
	
	@Column(name = "booking_paymentid")
	private int bookingPaymentId;

	public Booking() {
		super();
	}

	public Booking(int bookingId, int bookingUserId, int bookingJourneyId, Date bookingDate, String bookingStatus,
			int bookingTotalFare, int bookingnumpassengers, int bookingPaymentId) {
		super();
		this.bookingId = bookingId;
		this.bookingUserId = bookingUserId;
		this.bookingJourneyId = bookingJourneyId;
		this.bookingDate = bookingDate;
		this.bookingStatus = bookingStatus;
		this.bookingTotalFare = bookingTotalFare;
		this.bookingnumpassengers = bookingnumpassengers;
		this.bookingPaymentId = bookingPaymentId;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getBookingUserId() {
		return bookingUserId;
	}

	public void setBookingUserId(int bookingUserId) {
		this.bookingUserId = bookingUserId;
	}

	public int getBookingJourneyId() {
		return bookingJourneyId;
	}

	public void setBookingJourneyId(int bookingJourneyId) {
		this.bookingJourneyId = bookingJourneyId;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public int getBookingTotalFare() {
		return bookingTotalFare;
	}

	public void setBookingTotalFare(int bookingTotalFare) {
		this.bookingTotalFare = bookingTotalFare;
	}

	public int getBookingnumpassengers() {
		return bookingnumpassengers;
	}

	public void setBookingnumpassengers(int bookingnumpassengers) {
		this.bookingnumpassengers = bookingnumpassengers;
	}

	public int getBookingPaymentId() {
		return bookingPaymentId;
	}

	public void setBookingPaymentId(int bookingPaymentId) {
		this.bookingPaymentId = bookingPaymentId;
	}

}
