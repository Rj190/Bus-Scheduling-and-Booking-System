package com.crimsonlogic.busschedulingandbookingsystem.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Seat;

@Repository
public interface ISeatRepository extends JpaRepository<Seat, Integer>{

	
	

}
