package com.fiap.fiaparking.controller;


import com.fiap.fiaparking.model.Driver;
import com.fiap.fiaparking.model.Payment;
import com.fiap.fiaparking.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/formas-pagamento")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Driver> createPayment(@RequestBody Payment payment) {
        return new ResponseEntity<>(paymentService.createPayment(payment), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> obterFormaPagamento(@PathVariable String id) {
        Optional<Payment> formaPagamentoOptional = paymentService.getPaymentById(Long.valueOf(id));

        if (formaPagamentoOptional.isPresent()) {
            Payment formaPagamento = formaPagamentoOptional.get();
            return new ResponseEntity<>(formaPagamento, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
