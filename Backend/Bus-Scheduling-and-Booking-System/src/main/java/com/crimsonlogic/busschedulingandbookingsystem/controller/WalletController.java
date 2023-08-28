package com.crimsonlogic.busschedulingandbookingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Wallet;
import com.crimsonlogic.busschedulingandbookingsystem.service.IWalletService;

@RestController
@RequestMapping("/wallets")
public class WalletController {
    @Autowired
    private IWalletService walletServ;
    
    @GetMapping
    public List<Wallet> getAllWallets() {
        return walletServ.getAllWallets();
    }
    
    @GetMapping("/{getwalletbyid}/{id}")
    public Wallet getWalletById(@PathVariable ("id") int id) {
        return walletServ.getWalletById(id);
    }
    
    @PostMapping("/createwallet")
    public Wallet createWallet(@RequestBody Wallet wallet) {
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

