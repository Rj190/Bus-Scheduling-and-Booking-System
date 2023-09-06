package com.crimsonlogic.busschedulingandbookingsystem.service;

import java.util.List;
import java.util.Optional;

import com.crimsonlogic.busschedulingandbookingsystem.entity.User;
import com.crimsonlogic.busschedulingandbookingsystem.entity.Wallet;

public interface IWalletService {
	public List<Wallet> getAllWallets();
	public Wallet createWallet(Wallet wallet);
	public Wallet updateWalletbyId(Integer id, Wallet wallet);
	public void deleteWalletbyId(Integer id);
	Wallet getWalletById(Integer id);
	//Optional<Wallet> getWalletByUser(String username);

}
