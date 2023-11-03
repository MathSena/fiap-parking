package com.fiap.fiaparking.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReceiptDTO {
    @NotNull(message = "Parking session ID is required")
    private Long parkingSessionId;

    @NotBlank(message = "Receipt details are required")
    private String details;
}
