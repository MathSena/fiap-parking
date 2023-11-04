package com.fiap.fiaparking.model;

import com.fiap.fiaparking.dtos.PaymentDetailsDTO;
import com.fiap.fiaparking.enums.PaymentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "payment_details")
@Data
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Payment type is required")
    private PaymentType paymentType;

    @Getter
    @NotBlank(message = "Payment information is required")
    private String paymentInfo;

    @OneToOne(mappedBy = "paymentDetails", fetch = FetchType.LAZY)
    private Driver driver;

    public void isPixAndFixedParkingCompatible(boolean isFixedParking) {
        if (this.paymentType == PaymentType.PIX && !isFixedParking) {
            throw new IllegalArgumentException("PIX is only available for fixed parking periods.");
        }
    }

    private PaymentDetails convertToEntity(PaymentDetailsDTO paymentDetailsDTO) {
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setPaymentType(paymentDetailsDTO.getPaymentType());
        paymentDetails.setPaymentInfo(paymentDetailsDTO.getPaymentInfo());
        return paymentDetails;

    }
}
