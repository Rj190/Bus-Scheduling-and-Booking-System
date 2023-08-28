package com.crimsonlogic.busschedulingandbookingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Journey;
import com.crimsonlogic.busschedulingandbookingsystem.repository.IJourneyRepository;
@Service
public class JourneyServiceImpl implements IJourneyService {
	@Autowired                                
    private IJourneyRepository jourRepo;

	@Override
	public List<Journey> viewAllJourneys() {
		return jourRepo.findAll();
	}

	@Override
	public Journey viewJourneyById(int journeyId) {
		return jourRepo.findById(journeyId).get();
	}

	@Override
	public Journey insertJourney(Journey journey) {
		return  jourRepo.save(journey);
	}

	@Override
	public void deleteJourneyById(int journeyId) {
		jourRepo.deleteById(journeyId);

	}
	//to update the age

	@Override
	public Journey updateJourneyById(int journeyId, Journey newjourney) {
		  { 
			  Journey existingJourney=viewJourneyById(journeyId);  
			  if(existingJourney != null) {
				  existingJourney.setJourneyDate(newjourney.getJourneyDate());
				  
			  }else {
				  System.out.println("JourneyId not found");
			  }
			  return existingJourney; 
			  
		  }

	}
}
