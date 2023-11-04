package com.fiap.fiaparking.model;

import com.fiap.fiaparking.enums.PaymentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "payment_details")
@Data
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Payment type is required")
    private PaymentType paymentType;

    @NotBlank(message = "Payment information is required")
    private String paymentInfo;

    @OneToOne(mappedBy = "paymentDetails", fetch = FetchType.LAZY)
    private Driver driver;

    public boolean isPixAndFixedParkingCompatible(boolean isFixedParking) {
        if (this.paymentType == PaymentType.PIX && !isFixedParking) {
            throw new IllegalArgumentException("PIX is only available for fixed parking periods.");
        }
        return true;
    }
}
