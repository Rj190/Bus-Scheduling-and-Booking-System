package com.crimsonlogic.busschedulingandbookingsystem.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="journeydetails")
public class Journey {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer journeyId;
	
	@Column(name="journeydate")
	@NotNull
	private LocalDate journeyDate;

	public Journey() {
		super();
	}

	public Journey(int journeyId, @NotNull LocalDate journeyDate) {
		super();
		this.journeyId = journeyId;
		this.journeyDate = journeyDate;
	}

	public int getJourneyId() {
		return journeyId;
	}

	public void setJourneyId(int journeyId) {
		this.journeyId = journeyId;
	}

	public LocalDate getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(LocalDate journeyDate) {
		this.journeyDate = journeyDate;
	}

	@Override
	public String toString() {
		return "Journey [journeyId=" + journeyId + ", journeyDate=" + journeyDate + "]";
	}
	
}
