package com.fiap.fiaparking.service;

import com.fiap.fiaparking.model.Driver;
import com.fiap.fiaparking.model.Vehicle;

public interface VehicleService {
    Vehicle registerVehicle(Vehicle vehicle);
    Driver findVehicleById(Long vehicleId);
    // ... quaisquer outros métodos que você possa precisar
}

