package com.crimsonlogic.busschedulingandbookingsystem.service;

import java.util.List;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Journey;

public interface IJourneyService {
	public List<Journey> viewAllJourneys();
	public Journey viewJourneyById(int journeyId);
	public Journey insertJourney(Journey journey); 
	public void deleteJourneyById(int journeyId);
	public Journey updateJourneyById(int journeyId,Journey journey);
	

}
