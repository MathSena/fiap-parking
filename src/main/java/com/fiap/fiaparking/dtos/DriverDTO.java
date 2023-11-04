package com.fiap.fiaparking.dtos;


import com.fiap.fiaparking.model.Driver;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class DriverDTO {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "CPF is required")
    private String cpf;

    @NotBlank(message = "Address is required")
    private String address;

    @Email(message = "Email should be valid")
    private String email;

    private List<VehicleDTO> vehicles;

    private PaymentDetailsDTO paymentDetails;

    public DriverDTO() {
        this.paymentDetails = new PaymentDetailsDTO();
    }

    public static DriverDTO fromEntity(Driver driver) {
        DriverDTO dto = new DriverDTO();
        dto.setId(driver.getId());
        dto.setName(driver.getName());
        dto.setCpf(driver.getCpf());
        dto.setAddress(driver.getAddress());
        dto.setEmail(driver.getEmail());
        dto.setPaymentDetails(PaymentDetailsDTO.fromEntity(driver.getPaymentDetails()));

        if (driver.getVehicles() != null) {
            dto.setVehicles(driver.getVehicles().stream()
                    .map(VehicleDTO::fromEntity) // Convert each Vehicle to VehicleDTO
                    .collect(Collectors.toList()));
        }

        if (driver.getPaymentDetails() != null) {
            dto.setPaymentDetails(PaymentDetailsDTO.fromEntity(driver.getPaymentDetails()));
        } else {
            dto.setPaymentDetails(new PaymentDetailsDTO()); // ou deixe como null, dependendo da sua lógica de negócios
        }

        return dto;
    }

}
