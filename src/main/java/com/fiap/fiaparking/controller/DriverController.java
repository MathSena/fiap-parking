package com.fiap.fiaparking.controller;

import com.fiap.fiaparking.dtos.DriverDTO;
import com.fiap.fiaparking.model.Driver;
import com.fiap.fiaparking.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/drivers")
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<Driver> registerDriver(@RequestBody @Valid DriverDTO driverDTO) {
        Driver driver = driverService.registerDriver(driverDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(driver.getId())
                .toUri();
        return ResponseEntity.created(location)
                .body(driver);
    }

    @GetMapping
    public ResponseEntity<Iterable<Driver>> findAllDrivers() {
        Iterable<Driver> drivers = driverService.findAllDrivers();
        return ResponseEntity.ok(drivers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> findDriverById(@PathVariable Long id) {
        Driver driver = driverService.findDriverById(id);
        return ResponseEntity.ok(driver);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable Long id, @RequestBody @Valid DriverDTO driverDTO) {
        Driver driver = driverService.updateDriver(id, driverDTO);
        return ResponseEntity.ok(driver);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping("/{id}/vehicles")
    public ResponseEntity<Driver> findDriverVehicles(@PathVariable Long id) {
        Driver driver = driverService.findDriverVehicles(id);
        return ResponseEntity.ok(driver);
    }

    @GetMapping("/{id}/vehicles/{vehicleId}")
    public ResponseEntity<Driver> findDriverVehicleById(@PathVariable Long id, @PathVariable Long vehicleId) {
        Driver driver = driverService.findDriverVehicleById(id, vehicleId);
        return ResponseEntity.ok(driver);
    }

    @PostMapping("/{id}/vehicles")
    public ResponseEntity<Driver> registerDriverVehicle(@PathVariable Long id, @RequestBody @Valid DriverDTO driverDTO) {
        Driver driver = driverService.registerDriverVehicle(id, driverDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(driver.getId())
                .toUri();
        return ResponseEntity.created(location)
                .body(driver);
    }

    @PutMapping("/{id}/vehicles/{vehicleId}")
    public ResponseEntity<Driver> updateDriverVehicle(@PathVariable Long id, @PathVariable Long vehicleId, @RequestBody @Valid DriverDTO driverDTO) {
        Driver driver = driverService.updateDriverVehicle(id, vehicleId, driverDTO);
        return ResponseEntity.ok(driver);
    }

    @DeleteMapping("/{id}/vehicles/{vehicleId}")
    public ResponseEntity<Void> deleteDriverVehicle(@PathVariable Long id, @PathVariable Long vehicleId) {
        driverService.deleteDriverVehicle(id, vehicleId);
        return ResponseEntity.noContent()
                .build();
    }


}
