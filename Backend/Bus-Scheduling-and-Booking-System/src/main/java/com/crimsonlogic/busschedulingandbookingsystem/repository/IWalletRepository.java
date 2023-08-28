package com.crimsonlogic.busschedulingandbookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Wallet;

@Repository
public interface IWalletRepository extends JpaRepository<Wallet, Integer>{

}
