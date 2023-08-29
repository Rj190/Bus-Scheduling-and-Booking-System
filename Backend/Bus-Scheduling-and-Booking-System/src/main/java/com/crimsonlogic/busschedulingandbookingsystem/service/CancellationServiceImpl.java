package com.crimsonlogic.busschedulingandbookingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Cancellation;
import com.crimsonlogic.busschedulingandbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.repository.ICancellationRepository;


@Service
public class CancellationServiceImpl implements ICancellationService {

	
	@Autowired
	private ICancellationRepository cancellationRepo;
	
	@Override
	public List<Cancellation> getAllCancellation() {
		return cancellationRepo.findAll();
	}

	@Override
	public Cancellation createCancellation(Cancellation cancellation) {
		return cancellationRepo.save(cancellation);
	}

	@Override
	public Cancellation updateCancellation(Integer cancellationID, Cancellation cancellation) {
		getCancellationById(cancellationID);
		cancellation.setCancellationID(cancellationID);
		return cancellationRepo.save(cancellation);
	}

	@Override
	public void deleteCancellation(Integer cancellationID) {
		getCancellationById(cancellationID);
		cancellationRepo.deleteById(cancellationID);
		
	}

	@Override
	public Cancellation getCancellationById(Integer cancellationID) {
		return cancellationRepo.findById(cancellationID)
				.orElseThrow(() -> new ResourceNotFoundException("Cancellation", "id",cancellationID ));
	}

}
