package com.fiap.fiaparking.service.impl;

import com.fiap.fiaparking.model.PaymentDetails;
import com.fiap.fiaparking.repository.PaymentDetailsRepository;
import com.fiap.fiaparking.service.PaymentDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PaymentDetailsServiceImpl implements PaymentDetailsService {

    private final PaymentDetailsRepository paymentDetailsRepository;

    @Autowired
    public PaymentDetailsServiceImpl(PaymentDetailsRepository paymentDetailsRepository) {
        this.paymentDetailsRepository = paymentDetailsRepository;
    }

    @Override
    public PaymentDetails addPaymentDetails(PaymentDetails paymentDetails) {
        log.info("Adding payment details for driver ID: {}", paymentDetails.getDriver().getId());
        return paymentDetailsRepository.save(paymentDetails);
    }

    @Override
    public List<PaymentDetails> getPaymentDetailsByDriver(Long driverId) {
        log.info("Retrieving payment details for driver ID: {}", driverId);
        return paymentDetailsRepository.findByDriverId(driverId);
    }
}
