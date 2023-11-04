package com.fiap.fiaparking.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiap.fiaparking.model.Driver;
import com.fiap.fiaparking.model.Vehicle;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class VehicleDTO {
    @NotBlank(message = "License plate is required")
    private String licensePlate;

    @NotBlank(message = "Vehicle make is required")
    private String make;

    @NotBlank(message = "Vehicle model is required")
    private String model;

    private DriverDTO driver;

    public static VehicleDTO fromEntity(Vehicle vehicle) {
        VehicleDTO dto = new VehicleDTO();
        dto.setLicensePlate(vehicle.getLicensePlate());
        dto.setMake(vehicle.getMake());
        dto.setModel(vehicle.getModel());
        // Typically you wouldn't set the DriverDTO in the VehicleDTO to avoid circular reference
        // dto.setDriver(DriverDTO.fromEntity(vehicle.getDriver())); // Only if necessary
        return dto;
    }

    private Vehicle convertToEntity(VehicleDTO vehicleDTO, Driver driver) {
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate(vehicleDTO.getLicensePlate());
        vehicle.setMake(vehicleDTO.getMake());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setDriver(driver); // associa o veículo ao motorista aqui
        return vehicle;
    }

}