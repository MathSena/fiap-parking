package com.fiap.fiaparking.dtos;

import com.fiap.fiaparking.enums.PaymentType;
import com.fiap.fiaparking.model.PaymentDetails;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentDetailsDTO {
    @NotNull(message = "Payment type is required")
    private PaymentType paymentType;

    @NotBlank(message = "Payment information is required")
    private String paymentInfo;

    private String driver;

    @AssertTrue(message = "PIX não está disponível para este tipo de usuário no momento do cadastro.")
    private boolean isPixAvailable() {
        return paymentType != PaymentType.PIX;
    }

    public static PaymentDetailsDTO fromEntity(PaymentDetails paymentDetails) {
        PaymentDetailsDTO dto = new PaymentDetailsDTO();
        dto.setPaymentType(paymentDetails.getPaymentType());
        dto.setPaymentInfo(paymentDetails.getPaymentInfo());
        dto.setDriver(String.valueOf(paymentDetails.getDriver()));
        return dto;
    }
}
