package com.fiap.fiaparking.controller;

import com.fiap.fiaparking.dtos.PaymentDTO;
import com.fiap.fiaparking.model.Payment;
import com.fiap.fiaparking.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/formas-pagamento")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping()
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO paymentDTO) {
        return new ResponseEntity<>(paymentService.createPayment(paymentDTO), HttpStatus.CREATED);
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
