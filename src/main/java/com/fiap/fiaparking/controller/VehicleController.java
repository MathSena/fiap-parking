package com.fiap.fiaparking.controller;

import com.fiap.fiaparking.model.Vehicle;
import com.fiap.fiaparking.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller for managing vehicles.
 */
@RestController
@RequestMapping("api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    /**
     * POST /api/vehicles : Create a new vehicle.
     *
     * @param vehicle the vehicle to create.
     * @return the ResponseEntity with status 201 (Created) and with body the new vehicle, or with status 400 (Bad Request) if the vehicle has already an ID.
     */
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        Vehicle newVehicleDTO = vehicleService.createVehicle(vehicle);
        return new ResponseEntity<>(newVehicleDTO, HttpStatus.CREATED);
    }

    /**
     * GET /api/vehicles : Get all the vehicles.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of vehicles in body.
     */
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> allVehicles = vehicleService.getAllVehicles();
        return new ResponseEntity<>(allVehicles, HttpStatus.OK);
    }

    /**
     * GET /api/vehicles/{vehicleId} : Get the "vehicleId" vehicle.
     *
     * @param vehicleId the id of the vehicle to retrieve.
     * @return the ResponseEntity with status 200 (OK) and with body the vehicle, or with status 404 (Not Found).
     */
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
