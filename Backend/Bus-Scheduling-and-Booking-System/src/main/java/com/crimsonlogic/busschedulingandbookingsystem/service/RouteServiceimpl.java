package com.crimsonlogic.busschedulingandbookingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crimsonlogic.busschedulingandbookingsystem.entity.Route;
import com.crimsonlogic.busschedulingandbookingsystem.exception.RouteNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.repository.IRouteRepository;

import java.util.List;

@Service
public class RouteServiceimpl implements IRouteService {

    @Autowired
    private IRouteRepository routeRepository;

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public Route findRouteById(int routeId) throws RouteNotFoundException {
        return routeRepository.findById(routeId).get();
    }

    @Override
    public Route insertRoute(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public Route updateRoute(int routeId, Route updatedRoute) throws RouteNotFoundException {
        Route existingRoute = findRouteById(routeId);

        existingRoute.setDepartureCity(updatedRoute.getDepartureCity());
        existingRoute.setArrivalCity(updatedRoute.getArrivalCity());
        existingRoute.setDistance(updatedRoute.getDistance());
        existingRoute.setDuration(updatedRoute.getDuration());
        existingRoute.setFare(updatedRoute.getFare());

        return routeRepository.save(existingRoute);
    }

    @Override
    public void deleteRoute(int routeId) throws RouteNotFoundException {
        Route route = findRouteById(routeId);
        routeRepository.delete(route);
    }
}
