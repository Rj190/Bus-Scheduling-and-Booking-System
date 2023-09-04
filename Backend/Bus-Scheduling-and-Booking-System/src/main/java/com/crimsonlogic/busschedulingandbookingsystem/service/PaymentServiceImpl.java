package com.crimsonlogic.busschedulingandbookingsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.busschedulingandbookingsystem.entity.Payment;
import com.crimsonlogic.busschedulingandbookingsystem.exception.ResourceNotFoundException;
import com.crimsonlogic.busschedulingandbookingsystem.repository.IPaymentRepository;

@Service
public class PaymentServiceImpl implements IPaymentService {
	@Autowired
	private IPaymentRepository payRepo;

	@Override
	public List<Payment> viewAllPayments() {
		return payRepo.findAll();
	}

	@Override
	public Optional<Payment> viewPaymentById(int paymentId) {
		return payRepo.findById(paymentId);
	}

	@Override
	public Payment insertPayment(Payment payment) {
		return payRepo.save(payment);
	}

	@Override
	public void deletePaymentById(int paymentId) {
		payRepo.deleteById(paymentId);

	}

	@Override
	public Payment updatePaymentById(int paymentId, Payment newpayment) {
		{
			Payment existingPayment = viewPaymentById(paymentId)
					.orElseThrow(() -> new ResourceNotFoundException("Payment", "Payment ID", paymentId));
			
			existingPayment.setPaymentAmount(newpayment.getPaymentAmount());

			return existingPayment;
		}
	}

}
