package com.fiap.fiaparking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "receipts")
@Data
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_session_id")
    private ParkingSession parkingSession;

    @NotBlank(message = "Receipt details are required")
    private String details;

    @NotNull(message = "Issue date is required")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime issueDate;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private double cost;

    public void setAmount(double cost) {
        this.cost = cost;
    }
}