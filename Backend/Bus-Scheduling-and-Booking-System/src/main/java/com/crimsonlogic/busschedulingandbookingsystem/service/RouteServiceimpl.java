package com.crimsonlogic.busschedulingandbookingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crimsonlogic.busschedulingandbookingsystem.entity.Route;
import com.crimsonlogic.busschedulingandbookingsystem.exception.RouteNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.repository.IRouteRepository;

import java.util.List;

/**
 * Service implementation for managing routes.
 * 
 * @author Ash
 * @author rohitjadhav
 */
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
        return routeRepository.findById(routeId)
            .orElseThrow(() -> new RouteNotFoundException("Route not found with id: " + routeId));
    }

    /**
     * Creates a new route.
     *
     * @param route The route to be created.
     * @return The created route.
     */
    @Override
    public Route insertRoute(Route route) {
        return routeRepository.save(route);
    }

    /**
     * Updates an existing route.
     *
     * @param routeId      The ID of the route to be updated.
     * @param updatedRoute The updated route data.
     * @return The updated route.
     * @throws RouteNotFoundException If the route is not found.
     */
    @Override
    public Route updateRoute(int routeId, Route updatedRoute) throws RouteNotFoundException {
        Route existingRoute = findRouteById(routeId);

        // Update properties
        existingRoute.setDepartureCity(updatedRoute.getDepartureCity());
        existingRoute.setArrivalCity(updatedRoute.getArrivalCity());
        existingRoute.setDistance(updatedRoute.getDistance());
        existingRoute.setDuration(updatedRoute.getDuration());
        existingRoute.setFare(updatedRoute.getFare());

        return routeRepository.save(existingRoute);
    }

    /**
     * Deletes a route by its ID.
     *
     * @param routeId The ID of the route to be deleted.
     * @throws RouteNotFoundException If the route is not found.
     */
    @Override
    public void deleteRoute(int routeId) throws RouteNotFoundException {
        Route route = findRouteById(routeId);
        routeRepository.delete(route);
    }
}
