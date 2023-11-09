package com.fiap.fiaparking.service;


import com.fiap.fiaparking.model.Driver;
import com.fiap.fiaparking.model.Payment;

import java.util.Optional;

public interface PaymentService {

    Driver createPayment(Payment payment);

    Optional<Payment> getPaymentById(Long id);

}
