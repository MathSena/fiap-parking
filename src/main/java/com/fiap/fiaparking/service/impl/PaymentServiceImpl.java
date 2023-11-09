package com.fiap.fiaparking.service.impl;

import com.fiap.fiaparking.model.Driver;
import com.fiap.fiaparking.model.Payment;
import com.fiap.fiaparking.repository.DriverRepository;
import com.fiap.fiaparking.repository.PaymentRepository;
import com.fiap.fiaparking.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public Driver createPayment(Payment payment) {
        String driverId = String.valueOf(payment.getDriver().getId());
        Optional<Driver> driver = driverRepository.findById(Long.valueOf(driverId));
        if (driver.isEmpty()) {
            throw new DataIntegrityViolationException("Condutor com ID não encontrado: " + driverId);
        }

        Payment newPayment = paymentRepository.save(payment);
        Driver newDriver = driver.get();

        if (newDriver.getPaymentMethod() == null) {
            newDriver.setPaymentMethod((List<Payment>) newPayment);
        } else {
            newDriver.getPaymentMethod().add(newPayment);
        }
        return driverRepository.save(newDriver);
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }


}
