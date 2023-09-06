package com.crimsonlogic.busschedulingandbookingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.management.RuntimeErrorException;
import java.util.List;
import com.crimsonlogic.busschedulingandbookingsystem.entity.User;
import com.crimsonlogic.busschedulingandbookingsystem.entity.Wallet;
import com.crimsonlogic.busschedulingandbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.exception.RouteNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.service.IUserService;
import com.crimsonlogic.busschedulingandbookingsystem.service.IWalletService;

@CrossOrigin("*")
@RestController
@RequestMapping("/wallets")
public class WalletController {

	@Autowired
	private IWalletService walletServ;

	@Autowired
	private IUserService userService;

	@GetMapping
	public ResponseEntity<List<Wallet>> getAllWallets() {
		List<Wallet> wallets = walletServ.getAllWallets();
		return ResponseEntity.ok(wallets);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Wallet> getWalletById(@PathVariable int id) {
		try {
			Wallet wallet = walletServ.getWalletById(id);
			return ResponseEntity.ok(wallet);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PostMapping("/createwallet/{username}")
	public ResponseEntity<?> createWallet(@PathVariable("username") String userName) {
		User user = userService.findByUsername(userName).orElse(null);
		if (user == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
		}

		try {
			Wallet existingWallet = user.getWallet();

			if (existingWallet != null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wallet already exists");
			}
			Wallet wallet = new Wallet();
			wallet.setUser(user);
			Wallet createdWallet = walletServ.createWallet(wallet);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdWallet);
		} catch (RuntimeErrorException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create wallet");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Wallet> updateWallet(@PathVariable("id") int id, @RequestBody Wallet wallet) {
		try {
			Wallet updatedWallet = walletServ.updateWalletbyId(id, wallet);
			return ResponseEntity.ok(updatedWallet);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteWallet(@PathVariable int id) {
		try {
			walletServ.deleteWalletbyId(id);
			return ResponseEntity.noContent().build();
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

//	@ExceptionHandler(RouteNotFoundException.class)
//	public ResponseEntity<String> handleRouteNotFoundException(RouteNotFoundException ex) {
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//	}
//
//	/**
//	 * Handle other exceptions and return INTERNAL_SERVER_ERROR status.
//	 * 
//	 * @param ex Exception instance
//	 * @return ResponseEntity with INTERNAL_SERVER_ERROR status and error message
//	 */
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<String> handleGeneralException(Exception ex) {
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
//	}
}
