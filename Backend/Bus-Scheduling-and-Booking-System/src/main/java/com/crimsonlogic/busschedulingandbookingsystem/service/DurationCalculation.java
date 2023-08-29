package com.crimsonlogic.busschedulingandbookingsystem.service;

public class DurationCalculation {
	public static int calculateDuration(int distance, int averageSpeed) {
        return distance / averageSpeed;
    }
}