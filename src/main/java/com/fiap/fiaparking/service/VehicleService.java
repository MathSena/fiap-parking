package com.fiap.fiaparking.service;

import com.fiap.fiaparking.model.Vehicle;

import java.util.List;

public interface VehicleService {

    Vehicle createVehicle(Vehicle vehicleDTO);

    Vehicle getVehicleById(Long id);

    List<Vehicle> getAllVehicles();


}

