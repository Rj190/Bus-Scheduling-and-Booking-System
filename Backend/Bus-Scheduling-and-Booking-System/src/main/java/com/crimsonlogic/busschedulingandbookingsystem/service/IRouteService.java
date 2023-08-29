package com.crimsonlogic.busschedulingandbookingsystem.service;

import java.util.List;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Route;
import com.crimsonlogic.busschedulingandbookingsystem.exception.RouteNotFoundException;

public interface IRouteService {

	List<Route> getAllRoutes();

	Route insertRoute(Route route);

	Route findRouteById(int routeId) throws RouteNotFoundException;

	Route updateRoute(int routeId, Route updatedRoute) throws RouteNotFoundException;

	void deleteRoute(int routeId) throws RouteNotFoundException;

}
