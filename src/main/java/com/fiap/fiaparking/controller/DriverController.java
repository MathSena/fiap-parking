package com.fiap.fiaparking.controller;

import com.fiap.fiaparking.model.Driver;
import com.fiap.fiaparking.model.Vehicle;
import com.fiap.fiaparking.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/drivers")
    public ResponseEntity<Driver> createDriver(@RequestBody Driver driver) {
        // Antes de salvar o driver, associe cada veículo e método de pagamento a ele
        driver.getVehicles().forEach(vehicle -> vehicle.setDriver(driver));
        driver.getPaymentMethod().forEach(payment -> payment.setDriver(driver));


        Driver savedDriver = driverService.createDriver(driver);

        return new ResponseEntity<>(savedDriver, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Driver>> listAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    @GetMapping("/{driverId}")
    public ResponseEntity<Driver> getDriver(@PathVariable String driverId) {
        Optional<Driver> driver = Optional.ofNullable(driverService.getDriverById(Long.valueOf(driverId)));
        return driver.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{driverId}/vehicles")
    public Driver linkVehicleToDriver(@PathVariable Long driverId, @RequestParam String vehicleId) {
        return driverService.addVehicleToDriver(driverId, vehicleId);
    }

    @GetMapping("/{driverId}/vehicles")
    public ResponseEntity<List<Vehicle>> getDriverVehicles(@PathVariable String driverId) {
        return driverService.getDriverVehicles(driverId);
    }


}
