package com.fiap.fiaparking.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class DriverDTO {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Address is required")
    private String address;

    @Email(message = "Email should be valid")
    private String email;

    private List<VehicleDTO> vehicles;
}
