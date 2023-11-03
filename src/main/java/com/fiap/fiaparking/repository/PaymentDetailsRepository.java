package com.fiap.fiaparking.repository;

import com.fiap.fiaparking.model.Driver;
import com.fiap.fiaparking.model.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Long> {
    // Busca detalhes de pagamento de um condutor específico.
    List<PaymentDetails> findByDriver(Driver driver);
}