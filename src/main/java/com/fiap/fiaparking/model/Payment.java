package com.fiap.fiaparking.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.fiaparking.enums.PaymentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Entity
@Table(name = "payment")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentType type;

    @NotBlank(message = "Payment details are required")
    private String paymentDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    @JsonIgnore
    private Driver driver;
}
