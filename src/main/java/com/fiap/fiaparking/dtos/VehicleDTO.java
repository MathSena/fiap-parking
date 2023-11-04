package com.fiap.fiaparking.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @JsonBackReference
    private DriverDTO driver;
}