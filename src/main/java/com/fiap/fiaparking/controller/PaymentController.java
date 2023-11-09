package com.fiap.fiaparking.controller;

import com.fiap.fiaparking.model.Driver;
import com.fiap.fiaparking.model.Payment;
import com.fiap.fiaparking.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller for handling payment-related actions.
 */
@RestController
@RequestMapping("api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    /**
     * Create a new payment method for a driver.
     *
     * @param payment the payment method to be created
     * @return ResponseEntity with the created payment method and HTTP status
     */
    @PostMapping
    public ResponseEntity<Driver> createPayment(@RequestBody Payment payment) {
        return new ResponseEntity<>(paymentService.createPayment(payment), HttpStatus.CREATED);
    }

    /**
     * Retrieve a payment method by its ID.
     *
     * @param id the ID of the payment method to retrieve
     * @return ResponseEntity containing the payment method if found, or HTTP NOT FOUND status
     */
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPayment(@PathVariable String id) {
        Optional<Payment> paymentOptional = paymentService.getPaymentById(Long.valueOf(id));

        return paymentOptional
                .map(payment -> new ResponseEntity<>(payment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
