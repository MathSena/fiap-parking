package com.fiap.fiaparking.mappers;

import com.fiap.fiaparking.dtos.PaymentDTO;
import com.fiap.fiaparking.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DriverMapper.class})
public interface PaymentMapper {

    //TODO: Rever o mapeamendo da lista de Driver, atualmente esta sendo ignorada

    @Mapping(source = "id", target = "id")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "description", target = "paymentDetails")
    @Mapping(source = "driver", target = "driver", ignore = true)
    Payment toPaymentEntity(PaymentDTO paymentDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "paymentDetails", target = "description")
    @Mapping(source = "driver", target = "driver", ignore = true)
    PaymentDTO toPaymentDTO(Payment payment);

}
