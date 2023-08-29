package com.crimsonlogic.busschedulingandbookingsystem.service;

public class FareCalculation {
	public static float calculateFare(float baseFarePerKm, float additionalCharges, int distance) {
        return baseFarePerKm * distance + additionalCharges;
    }}
