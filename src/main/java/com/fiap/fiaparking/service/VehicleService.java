package com.fiap.fiaparking.service;

import com.fiap.fiaparking.dtos.VehicleDTO;
import com.fiap.fiaparking.model.Vehicle;

import java.util.List;

public interface VehicleService {

    VehicleDTO createVehicle(VehicleDTO vehicleDTO);

    Vehicle updateVehicle(Vehicle vehicle);

    VehicleDTO getVehicleById(Long id);

    List<VehicleDTO> getAllVehicles();


}

