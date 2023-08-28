package com.crimsonlogic.busschedulingandbookingsystem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import com.crimsonlogic.busschedulingandbookingsystem.entity.Payment;
import com.crimsonlogic.busschedulingandbookingsystem.service.PaymentServiceImpl;

public class PaymentControllerTest {

    @Mock
    private PaymentServiceImpl paymentService;

    @InjectMocks
    private PaymentController paymentController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePayment() {
        Payment mockPayment = new Payment();
        mockPayment.setPaymentId(1);
        mockPayment.setPaymentAmount(50.0);
        mockPayment.setPaymentDate(LocalDate.of(2023, 8, 28));

        when(paymentService.insertPayment(any(Payment.class))).thenReturn(mockPayment);

        Payment paymentToCreate = new Payment();
        paymentToCreate.setPaymentAmount(50.0);
        paymentToCreate.setPaymentDate(LocalDate.of(2023, 8, 28));

        Payment createdPayment = paymentController.insertPayment(paymentToCreate);

        assertEquals(mockPayment, createdPayment);

        verify(paymentService, times(1)).insertPayment(eq(paymentToCreate));
    }

    @Test
    public void testGetPaymentById() {
        Payment mockPayment = new Payment();
        mockPayment.setPaymentId(1);
        mockPayment.setPaymentAmount(75.0);
        mockPayment.setPaymentDate(LocalDate.of(2023, 8, 29));

        when(paymentService.viewPaymentById(1)).thenReturn(mockPayment);

        Payment retrievedPayment = paymentController.viewPaymentById(1);

        assertEquals(mockPayment, retrievedPayment);

        verify(paymentService, times(1)).viewPaymentById(eq(1));
    }
    @Test
    public void testDeletePayment() {
        int paymentIdToDelete = 1;

        paymentController.deletePayment(paymentIdToDelete);

        verify(paymentService, times(1)).deletePaymentById(eq(paymentIdToDelete));
    }
    
    @Test
    public void testUpdatePayment() {
        Payment mockPayment = new Payment();
        mockPayment.setPaymentId(1);
        mockPayment.setPaymentAmount(50.0);
        mockPayment.setPaymentDate(LocalDate.of(2023, 8, 28));

        when(paymentService.updatePaymentById(anyInt(), any(Payment.class))).thenReturn(mockPayment);

        Payment paymentToUpdate = new Payment();
        paymentToUpdate.setPaymentAmount(60.0);
        paymentToUpdate.setPaymentDate(LocalDate.of(2023, 8, 28));

        Payment updatedPayment = paymentController.updatePaymentById(1, paymentToUpdate);

        assertEquals(mockPayment, updatedPayment);

        verify(paymentService, times(1)).updatePaymentById(eq(1), eq(paymentToUpdate));
    }
}
