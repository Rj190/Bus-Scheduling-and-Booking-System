package com.crimsonlogic.busschedulingandbookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{

}