package com.fiap.fiaparking.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ParkingSessionDTO {
    @NotNull(message = "Vehicle ID is required")
    private Long vehicleId;

    @NotNull(message = "Start time is required")
    private LocalDateTime startTime; // Alterado de Date para LocalDateTime para melhor manipulação de datas e horas

    private LocalDateTime endTime; // Alterado de Date para LocalDateTime

    private boolean isFixed; // Indica se a sessão de estacionamento é para um período fixo
}
