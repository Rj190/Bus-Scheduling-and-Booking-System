package com.crimsonlogic.busschedulingandbookingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Route;
import com.crimsonlogic.busschedulingandbookingsystem.exception.RouteNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.service.IRouteService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin("*")
@RestController
@RequestMapping("/routes")
public class RouteController {

	    @Autowired
	    private IRouteService routeService;

	    @GetMapping("/getallroutes")
	    public List<Route> getAllRoutes() {
	        return routeService.getAllRoutes();
	    }

	    @GetMapping("/getroutebyid/{routeId}")
	    public Route getRouteById(@PathVariable ("routeId")Integer routeId) throws RouteNotFoundException {
	        return routeService.findRouteById(routeId);
	    }

	    @PostMapping("/createroute")
	    public Route createRoute(@RequestBody Route route) {
	        return routeService.insertRoute(route);
	     
	    }

	    @PutMapping("/updateroute/{routeId}")
	    public Route updateRoute(@PathVariable("routeId") Integer routeId, @RequestBody Route updatedRoute)
	            throws RouteNotFoundException {
	       return routeService.updateRoute(routeId, updatedRoute);
	    }

	    @DeleteMapping("/deleteroute/{routeId}")
	    public  void deleteRoute(@PathVariable("routeId") Integer routeId) throws RouteNotFoundException {
	        routeService.deleteRoute(routeId);
			
	    }
	}


