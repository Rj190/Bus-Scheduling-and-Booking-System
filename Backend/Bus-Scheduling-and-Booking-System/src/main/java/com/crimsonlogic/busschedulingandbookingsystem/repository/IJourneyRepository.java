package com.crimsonlogic.busschedulingandbookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Journey;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface IJourneyRepository extends JpaRepository<Journey, Integer> {
    
    List<Journey> findAllByJourneyDate(LocalDate journeyDate);

    List<Journey> findAllByJourneyTimeBetween(LocalTime startTime, LocalTime endTime);

    List<Journey> findAllByBus_BusId(Integer busId);

    List<Journey> findAllByRoute_RouteId(Integer routeId);

    List<Journey> findAllByBus_BusIdAndJourneyDate(Integer busId, LocalDate journeyDate);

    List<Journey> findAllByRoute_RouteIdAndJourneyDate(Integer routeId, LocalDate journeyDate);


}
