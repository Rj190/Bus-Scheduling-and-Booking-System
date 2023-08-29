package com.crimsonlogic.busschedulingandbookingsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "routedetails")
public class Route {
 
	@Id
	@Column(name="routeid")
	private Integer routeId;
	
	@Column(name ="departure city")
	@Pattern(regexp = "^[a-zA-Z]+$")
	private String departureCity;
	
	@Column(name ="arrival city")
	@Pattern(regexp = "^[a-zA-Z]+$")
	private String arrivalCity;
	
	@Column(name ="distance")
	private Integer distance;
	
	@Column(name ="duration")
	private Integer duration;
	
	@Column(name ="fare")
	private float fare;

	public Route() {
		super();
	}

	public Route(Integer routeId, @Pattern(regexp = "^[a-zA-Z]+$") String departureCity,
			@Pattern(regexp = "^[a-zA-Z]+$") String arrivalCity, Integer distance, Integer duration, float fare) {
		super();
		this.routeId = routeId;
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
		this.distance = distance;
		this.duration = duration;
		this.fare = fare;
	}

	public Integer getRouteId() {
		return routeId;
	}

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public float getFare() {
		return fare;
	}

	public void setFare(float fare) {
		this.fare = fare;
	}

	@Override
	public String toString() {
		return "Route [routeId=" + routeId + ", departureCity=" + departureCity + ", arrivalCity=" + arrivalCity
				+ ", distance=" + distance + ", duration=" + duration + ", fare=" + fare + "]";
	}
}
