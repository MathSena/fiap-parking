package com.fiap.fiaparking.dtos;

import com.fiap.fiaparking.enums.PaymentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentDetailsDTO {
    @NotNull(message = "Payment type is required")
    private PaymentType paymentType;

    @NotBlank(message = "Payment information is required")
    private String paymentInfo;
}
