package com.crimsonlogic.busschedulingandbookingsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Cancellation;


public interface ICancellationService {
	
	
	List<Cancellation> getAllCancellation();
	
	Cancellation createCancellation(Cancellation cancellation);
	Cancellation updateCancellation(Integer cancellationID,Cancellation cancellation);
    void deleteCancellation(Integer cancellationID);
    Cancellation getCancellationById(Integer cancellationID);

}
