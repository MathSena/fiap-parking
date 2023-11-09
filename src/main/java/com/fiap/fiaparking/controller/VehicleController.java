package com.fiap.fiaparking.controller;


import com.fiap.fiaparking.model.Vehicle;
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
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        Vehicle newVehicleDTO = vehicleService.createVehicle(vehicle);
        return new ResponseEntity<>(newVehicleDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> allVehicles = vehicleService.getAllVehicles();
        return new ResponseEntity<>(allVehicles, HttpStatus.OK);
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable Long vehicleId) {
        try {
            Vehicle newVehicle = vehicleService.getVehicleById(vehicleId);
            return new ResponseEntity<>(newVehicle, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
