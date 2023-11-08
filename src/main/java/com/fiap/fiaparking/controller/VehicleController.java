package com.fiap.fiaparking.controller;

import com.fiap.fiaparking.dtos.VehicleDTO;
import com.fiap.fiaparking.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleDTO> createVehicle(@RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO newVehicleDTO = vehicleService.createVehicle(vehicleDTO);
        return new ResponseEntity<>(newVehicleDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        List<VehicleDTO> allVehicles = vehicleService.getAllVehicles();
        return new ResponseEntity<>(allVehicles, HttpStatus.OK);
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<VehicleDTO> getVehicle(@PathVariable Long vehicleId) {
        try {
            VehicleDTO newVehicleDTO = vehicleService.getVehicleById(vehicleId);
            return new ResponseEntity<>(newVehicleDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
