package com.fiap.fiaparking.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties
public class DriverDTO {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "CPF is required")
    private String cpf;

    @Email(message = "Email should be valid")
    private String email;

    @JsonManagedReference
    private List<VehicleDTO> vehicles;
}
