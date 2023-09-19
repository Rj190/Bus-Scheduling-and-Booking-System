package com.crimsonlogic.busschedulingandbookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Route;

public interface IRouteRepository extends JpaRepository<Route, Integer> {
    Route findByDepartureCityAndArrivalCity(String departureCity, String arrivalCity);

}
