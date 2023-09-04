package com.crimsonlogic.busschedulingandbookingsystem.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Bus;
import com.crimsonlogic.busschedulingandbookingsystem.entity.Seat;

@Repository
public interface ISeatRepository extends JpaRepository<Seat, Integer>{	
	List<Seat> findAllByBus(Bus bus);

}
