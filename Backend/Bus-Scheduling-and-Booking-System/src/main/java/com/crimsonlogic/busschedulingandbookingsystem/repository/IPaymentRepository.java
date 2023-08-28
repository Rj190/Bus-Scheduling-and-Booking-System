package com.crimsonlogic.busschedulingandbookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Payment;
@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Integer>{
	

}
