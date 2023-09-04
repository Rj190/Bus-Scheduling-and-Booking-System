package com.crimsonlogic.busschedulingandbookingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Bus;
import com.crimsonlogic.busschedulingandbookingsystem.entity.Journey;
import com.crimsonlogic.busschedulingandbookingsystem.entity.Route;
import com.crimsonlogic.busschedulingandbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.repository.IJourneyRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class JourneyServiceImpl implements IJourneyService {

    @Autowired
    private IJourneyRepository journeyRepository;

    @Override
    public List<Journey> viewAllJourneys() {
        return journeyRepository.findAll();
    }

    @Override
    public Journey viewJourneyById(int journeyId) {
        return journeyRepository.findById(journeyId)
                .orElseThrow(() -> new ResourceNotFoundException("Journey", "journeyId", journeyId));
    }

    @Override
    public Journey  insertJourney(Journey journey){
        return journeyRepository.save(journey);
    }

    @Override
    public void deleteJourneyById(int journeyId) {
        if (!journeyRepository.existsById(journeyId)) {
            throw new ResourceNotFoundException("Journey", "journeyId", journeyId);
        }
        journeyRepository.deleteById(journeyId);
    }

    @Override
    public Journey updateJourneyById(int journeyId, Journey newJourney) {
        Journey existingJourney = journeyRepository.findById(journeyId)
                .orElseThrow(() -> new ResourceNotFoundException("Journey", "journeyId", journeyId));

        existingJourney.setJourneyDate(newJourney.getJourneyDate());
        existingJourney.setJourneyTime(newJourney.getJourneyTime());

        return journeyRepository.save(existingJourney);
    }

    @Override
    public List<Journey> viewJourneysByDate(LocalDate journeyDate) {
        return journeyRepository.findAllByJourneyDate(journeyDate);
    }

    @Override
    public List<Journey> viewJourneysByTime(LocalTime startTime, LocalTime endTime) {
        return journeyRepository.findAllByJourneyTimeBetween(startTime, endTime);
    }

    @Override
    public List<Journey> viewJourneysByBus(Integer busId) {
        return journeyRepository.findAllByBus_BusId(busId);
    }

    @Override
    public List<Journey> viewJourneysByRoute(Integer routeId) {
        return journeyRepository.findAllByRoute_RouteId(routeId);
    }

    @Override
    public List<Journey> viewJourneysByBusAndDate(Integer busId, LocalDate journeyDate) {
        return journeyRepository.findAllByBus_BusIdAndJourneyDate(busId, journeyDate);
    }

    @Override
    public List<Journey> viewJourneysByRouteAndDate(Integer routeId, LocalDate journeyDate) {
        return journeyRepository.findAllByRoute_RouteIdAndJourneyDate(routeId, journeyDate);
    }
}
