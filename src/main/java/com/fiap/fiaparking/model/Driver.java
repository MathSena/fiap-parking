package com.fiap.fiaparking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "drivers")
@Data
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "CPF is required")
    private String cpf;

    @NotBlank(message = "Address is required")
    private String address;

    @Email(message = "Email should be valid")
    private String email;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicle> vehicles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_details_id", referencedColumnName = "id")
    private PaymentDetails paymentDetails;

    public Vehicle getVehicleById(Long vehicleId) {
        if (vehicles != null) {
            for (Vehicle vehicle : vehicles) {
                if (vehicle.getId().equals(vehicleId)) {
                    return vehicle;
                }
            }
        }
        return null; // ou opcionalmente lançar uma exceção se preferir
    }


}
