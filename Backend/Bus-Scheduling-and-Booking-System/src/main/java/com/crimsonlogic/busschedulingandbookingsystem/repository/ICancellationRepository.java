package com.crimsonlogic.busschedulingandbookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Cancellation;

@Repository
public interface ICancellationRepository extends JpaRepository<Cancellation,Integer>{
	
	
	

}
