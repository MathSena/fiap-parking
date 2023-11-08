package com.fiap.fiaparking.service;

import com.fiap.fiaparking.dtos.PaymentDTO;
import com.fiap.fiaparking.model.Payment;

import java.util.Optional;

public interface PaymentService {

    PaymentDTO createPayment(PaymentDTO paymentDTO);

    Optional<Payment> getPaymentById(Long id);

}
