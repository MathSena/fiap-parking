package com.fiap.fiaparking.dtos;


import lombok.Data;


@Data
public class DriverDTO {

    private Long id;
    private String name;
    private String cpf;
    private String address;
    private String email;
    private PaymentDTO paymentMethod;


}
