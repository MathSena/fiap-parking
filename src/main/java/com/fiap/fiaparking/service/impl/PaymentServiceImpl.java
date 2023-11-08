package com.fiap.fiaparking.service.impl;

import com.fiap.fiaparking.dtos.PaymentDTO;
import com.fiap.fiaparking.mappers.PaymentMapper;
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

    @Autowired
    private PaymentMapper paymentMapper;

    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        String driverId = String.valueOf(paymentDTO.getDriver().getId());
        Optional<Driver> driver = driverRepository.findById(Long.valueOf(driverId));
        if (driver.isEmpty()) {
            throw new DataIntegrityViolationException("Condutor com ID não encontrado: " + driverId);
        }

        Payment newPayment = paymentRepository.save(paymentMapper.toPaymentEntity(paymentDTO));
        Driver newDriver = driver.get();

        if (newDriver.getPaymentMethod() == null) {
            newDriver.setPaymentMethod((List<Payment>) newPayment);
        } else {
            newDriver.getPaymentMethod().add(newPayment);
        }
        driverRepository.save(newDriver);
        return paymentMapper.toPaymentDTO(newPayment);
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }


}
