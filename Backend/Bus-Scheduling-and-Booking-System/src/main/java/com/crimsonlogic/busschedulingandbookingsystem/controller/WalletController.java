package com.crimsonlogic.busschedulingandbookingsystem.controller;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonlogic.busschedulingandbookingsystem.entity.User;
import com.crimsonlogic.busschedulingandbookingsystem.entity.Wallet;
import com.crimsonlogic.busschedulingandbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.service.IUserService;
import com.crimsonlogic.busschedulingandbookingsystem.service.IWalletService;

@RestController
@RequestMapping("/wallets")
public class WalletController {
    @Autowired
    private IWalletService walletServ;
    
	@Autowired
	private IUserService userService;
    
    @GetMapping
    public List<Wallet> getAllWallets() {
        return walletServ.getAllWallets();
    }
    
    @GetMapping("/{getwalletbyid}/{id}")
    public Wallet getWalletById(@PathVariable ("id") int id) {
        return walletServ.getWalletById(id);
    }
    
    @PostMapping("/createwallet/{username}")
    public Wallet createWallet(@PathVariable("username") String userName ,@RequestBody Wallet wallet) {
    	User user = userService.findByUsername(userName).get();
    		Wallet exWallet = walletServ.getWalletByUser(user);
    		if(exWallet != null)
    			
    			throw new RuntimeErrorException(null, "Wallet Already existing..");
    		else
    			
        return walletServ.createWallet(wallet);
    }
    
    @PutMapping("/{updatewalletbyid}/{id}")
    public Wallet updateWallet(@PathVariable ("id")  int id, @RequestBody Wallet wallet) {
        return walletServ.updateWalletbyId(id, wallet);
    }
    
    @DeleteMapping("/{deletewalletbyid}/{id}")
    public void deleteWallet(@PathVariable ("id") int id) {
    	walletServ.deleteWalletbyId(id);
    }
}

