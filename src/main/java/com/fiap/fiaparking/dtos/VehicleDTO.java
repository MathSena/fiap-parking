package com.fiap.fiaparking.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VehicleDTO {
    @NotBlank(message = "License plate is required")
    private String licensePlate;

    @NotBlank(message = "Vehicle make is required")
    private String make;

    @NotBlank(message = "Vehicle model is required")
    private String model;
}