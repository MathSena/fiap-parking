package com.fiap.fiaparking.service;

import com.fiap.fiaparking.model.PaymentDetails;

import java.util.List;

public interface PaymentDetailsService {
    PaymentDetails addPaymentDetails(PaymentDetails paymentDetails);
    List<PaymentDetails> getPaymentDetailsByDriver(Long driverId);
}
