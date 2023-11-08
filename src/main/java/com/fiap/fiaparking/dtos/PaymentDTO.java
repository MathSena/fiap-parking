package com.fiap.fiaparking.dtos;

import com.fiap.fiaparking.enums.PaymentType;
import lombok.Data;

@Data
public class PaymentDTO {

    private Long id;
    private String description;
    private PaymentType type;
    private DriverDTO driver;

}
