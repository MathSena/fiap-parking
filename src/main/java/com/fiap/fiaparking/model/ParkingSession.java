package com.fiap.fiaparking.model;

import com.fiap.fiaparking.enums.DurationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Table(name = "parking_sessions")
@Data
public class ParkingSession {

    @Id
    private String id;

    @NotNull(message = "Entry time cannot be null")
    @Column(name = "entry_time")
    private LocalDateTime entry;

    @Column(name = "exit_time")
    private LocalDateTime exit;

    @NotNull(message = "Parking time type cannot be null")
    @Column(name = "parking_time_type")
    private DurationType type;


    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;


    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;


    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;


    @Column(name = "value")
    private double value;


}
