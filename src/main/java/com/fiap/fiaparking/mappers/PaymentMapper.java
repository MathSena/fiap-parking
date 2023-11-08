package com.fiap.fiaparking.mappers;

import com.fiap.fiaparking.dtos.PaymentDTO;
import com.fiap.fiaparking.model.Payment;
import org.mapstruct.Mapper;

@Mapper
public interface PaymentMapper {

    Payment toPaymentEntity(PaymentDTO paymentDTO);

    PaymentDTO toPaymentDTO(Payment payment);

}
