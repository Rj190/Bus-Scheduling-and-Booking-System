package com.crimsonlogic.busschedulingandbookingsystem.service;

import java.util.List;
import java.util.Optional;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Payment;

public interface IPaymentService {
	public List<Payment> viewAllPayments();
	public Optional<Payment> viewPaymentById(int paymentId);
	public Payment insertPayment(Payment payment); 
	public void deletePaymentById(int paymentId);
	public Payment updatePaymentById(int paymentId,Payment payment);

}
