package com.crimsonlogic.busschedulingandbookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Wallet;
import com.crimsonlogic.busschedulingandbookingsystem.entity.User;
import java.util.List;
import java.util.Optional;

@Repository
public interface IWalletRepository extends JpaRepository<Wallet, Integer>{
	
//	Optional<Wallet> findByUser(String userName);

}
