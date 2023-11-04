package com.fiap.fiaparking.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.Duration;

@Entity
@Table(name = "receipts")
@Data
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_session_id", referencedColumnName = "id")
    private ParkingSession parkingSession;

    @Column(nullable = false)
    private String details;

    @Column(nullable = false)
    private LocalDateTime issueDate;

    @Column(nullable = false)
    private double cost;

    private Duration parkedDuration;
    private double hourlyRate;


    public Receipt(ParkingSession session, double hourlyRate) {
        this.parkingSession = session;
        this.issueDate = LocalDateTime.now();
        this.hourlyRate = hourlyRate;
        this.parkedDuration = Duration.between(session.getStartTime(), session.getEndTime());
        this.cost = session.getTotalCharge();
        this.details = String.format("Tempo estacionado: %s horas. Tarifa: R$%.2f/hora. Valor total: R$%.2f",
                parkedDuration.toHours(), hourlyRate, cost);
    }

    public Receipt() {

    }

    public void setCost(double cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.cost = cost;
    }
}
