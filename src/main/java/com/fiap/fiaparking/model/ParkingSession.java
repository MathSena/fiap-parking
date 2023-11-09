package com.fiap.fiaparking.model;

import com.fiap.fiaparking.enums.DurationType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Table(name = "parking")
@Data
public class ParkingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "entry_time")
    private LocalDateTime entry;

    @Column(name = "exit_time")
    private LocalDateTime exit;

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


    @Column(name = "parkingValue")
    private double parkingValue;


}
