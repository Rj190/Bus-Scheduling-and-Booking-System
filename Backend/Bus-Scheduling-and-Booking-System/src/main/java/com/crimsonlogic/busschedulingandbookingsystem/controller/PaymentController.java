package com.crimsonlogic.busschedulingandbookingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Payment;
import com.crimsonlogic.busschedulingandbookingsystem.entity.Seat;
import com.crimsonlogic.busschedulingandbookingsystem.entity.User;
import com.crimsonlogic.busschedulingandbookingsystem.entity.Wallet;
import com.crimsonlogic.busschedulingandbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.service.IPaymentService;
import com.crimsonlogic.busschedulingandbookingsystem.service.IUserService;
import com.crimsonlogic.busschedulingandbookingsystem.service.IWalletService;

@CrossOrigin("*")
@RestController
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	private IPaymentService payService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IWalletService walletService;

	@GetMapping()
	@RequestMapping("/viewallpayments")
	public List<Payment> viewAllPayments() {
		return payService.viewAllPayments();
	}

	@PostMapping("/insertpatment/{user}")
	public Payment insertPayment(@PathVariable("user") String userName, @RequestBody Payment payment) {
		User user = userService.findByUsername(userName)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userName", userName));
		Wallet wallet = user.getWallet();
		payment.setWallet(wallet);
		return payService.insertPayment(payment);
	}

	@GetMapping("/getpaymentsbyid/{payid}")
	public Payment viewPaymentById(@PathVariable("payid") Integer paymentId) {
		return payService.viewPaymentById(paymentId).orElseThrow(() -> new ResourceNotFoundException("Payment", "PaymentId",paymentId));
	}

	@DeleteMapping("/deletepayment/{payid}")
	public void deletePayment(@PathVariable("payid") int paymentid) {
		payService.deletePaymentById(paymentid);
	}

	@PutMapping("/updatepaymentsbyid/{payid}")
	public Payment updatePaymentById(@PathVariable("payid") int paymentId, @RequestBody Payment newPayment) {
		return payService.updatePaymentById(paymentId, newPayment);
	}

}
