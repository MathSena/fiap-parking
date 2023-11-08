package com.fiap.fiaparking.controller;

import com.fiap.fiaparking.model.Driver;
import com.fiap.fiaparking.model.Vehicle;
import com.fiap.fiaparking.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping()
    public ResponseEntity<Driver> registerDriver(@RequestBody Driver driver) {
        Driver newDriver = driverService.createDriver(driver);
        return new ResponseEntity<>(newDriver, HttpStatus.CREATED);
    }

    @GetMapping()
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
    public Driver linkVehicleToDriver(@PathVariable Long driverId,
                                      @RequestParam String vehicleId) {
        return driverService.addVehicleToDriver(driverId, vehicleId);
    }

    @GetMapping("/{driverId}/vehicles")
    public ResponseEntity<List<Vehicle>> getDriverVehicles(@PathVariable String driverId) {
        return driverService.getDriverVehicles(driverId);
    }


}
