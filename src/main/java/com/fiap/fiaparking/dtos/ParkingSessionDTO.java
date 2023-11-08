package com.fiap.fiaparking.dtos;

import com.fiap.fiaparking.enums.DurationType;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ParkingSessionDTO {

    private LocalDateTime entry;
    private LocalDateTime exit;
    private DurationType type;
    private DriverDTO driverId;
    private VehicleDTO vehicleId;
    private PaymentDTO paymentMethodId;

}
