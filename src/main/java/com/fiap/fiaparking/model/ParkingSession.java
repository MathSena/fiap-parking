package com.fiap.fiaparking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;


@Entity
@Table(name = "parking_sessions")
@Data
public class ParkingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @NotNull(message = "Start time is required")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startTime;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endTime;


    private boolean isFixed;

    @NotNull(message = "Total charge must be defined")
    @Positive(message = "Total charge must be positive")
    private Double totalCharge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_details_id")
    private PaymentDetails paymentDetails;

    @Getter
    @Column(name = "payment_processed")
    private boolean paymentProcessed = false;

    public void setPaymentProcessed(boolean paymentProcessed) {
        this.paymentProcessed = paymentProcessed;
    }
}
