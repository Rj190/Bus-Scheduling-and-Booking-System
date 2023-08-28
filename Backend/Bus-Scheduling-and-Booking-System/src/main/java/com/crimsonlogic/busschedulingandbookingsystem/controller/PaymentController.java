package com.crimsonlogic.busschedulingandbookingsystem.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.crimsonlogic.busschedulingandbookingsystem.entity.Payment;
import com.crimsonlogic.busschedulingandbookingsystem.service.IPaymentService;

@Controller
@RestController
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	private IPaymentService payService;
	
	
	@GetMapping()
	@RequestMapping("/viewallpayments")
	public List<Payment> viewAllPayments(){
return payService.viewAllPayments();
}
	@PostMapping("/insertpayment")   
    public Payment insertPayment(@RequestBody Payment payment) {
	return payService.insertPayment(payment);
	}
	  @GetMapping("/getpaymentsbyid/{payid}")
			public Payment viewPaymentById(@PathVariable("payid")int paymentId)  {
				return payService.viewPaymentById(paymentId);
			}
	  @DeleteMapping("/deletepayment/{payid}")
		public void deletePayment(@PathVariable("payid")int paymentid) {
			payService.deletePaymentById(paymentid);
		}
	  @PutMapping("/updatepaymentsbyid/{payid}")
	  	public Payment updatePaymentById(@PathVariable("payid")int paymentId,
	  			   @RequestBody Payment newPayment)   {
	  		return payService.updatePaymentById(paymentId,newPayment);
	  	}

}

