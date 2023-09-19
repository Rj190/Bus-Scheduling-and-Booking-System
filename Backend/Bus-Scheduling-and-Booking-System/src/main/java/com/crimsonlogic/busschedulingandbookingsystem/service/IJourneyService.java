package com.crimsonlogic.busschedulingandbookingsystem.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Bus;
import com.crimsonlogic.busschedulingandbookingsystem.entity.Journey;
import com.crimsonlogic.busschedulingandbookingsystem.entity.Route;

public interface IJourneyService {
	List<Journey> viewAllJourneys();

	Journey viewJourneyById(int journeyId);

	Journey insertJourney(Journey journey);

	void deleteJourneyById(int journeyId);

	Journey updateJourneyById(int journeyId, Journey journey);

	List<Journey> viewJourneysByDate(LocalDate journeyDate);

	List<Journey> viewJourneysByTime(LocalTime startTime, LocalTime endTime);

	List<Journey> viewJourneysByBus(Integer busId);

	List<Journey> viewJourneysByRoute(Integer routeId);

	List<Journey> viewJourneysByBusAndDate(Integer busId, LocalDate journeyDate);

	List<Journey> viewJourneysByRouteAndDate(Integer routeId, LocalDate journeyDate);

	List<Journey> findJourneysByDateAndCities(LocalDate journeyDate, String departureCity, String arrivalCity);
}
