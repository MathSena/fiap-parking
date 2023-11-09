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

/**
 * Controller for handling requests related to drivers.
 */
@RestController
@RequestMapping("api/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    /**
     * Create a new driver along with their vehicles and payment methods.
     *
     * @param driver the driver to be created
     * @return ResponseEntity containing the created driver and HTTP status
     */
    @PostMapping("/drivers")
    public ResponseEntity<Driver> createDriver(@RequestBody Driver driver) {
        // Before saving the driver, associate each vehicle and payment method with the driver
        driver.getVehicles().forEach(vehicle -> vehicle.setDriver(driver));
        driver.getPaymentMethod().forEach(payment -> payment.setDriver(driver));

        Driver savedDriver = driverService.createDriver(driver);
        return new ResponseEntity<>(savedDriver, HttpStatus.CREATED);
    }

    /**
     * List all registered drivers.
     *
     * @return ResponseEntity containing the list of all drivers and HTTP status
     */
    @GetMapping
    public ResponseEntity<List<Driver>> listAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    /**
     * Retrieve a driver by their ID.
     *
     * @param driverId the ID of the driver to retrieve
     * @return ResponseEntity containing the requested driver if found, or HTTP NOT FOUND status
     */
    @GetMapping("/{driverId}")
    public ResponseEntity<Driver> getDriver(@PathVariable String driverId) {
        Optional<Driver> driver = Optional.ofNullable(driverService.getDriverById(Long.valueOf(driverId)));
        return driver.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Link a vehicle to a driver by their IDs.
     *
     * @param driverId the ID of the driver
     * @param vehicleId the ID of the vehicle to link
     * @return the updated driver with the linked vehicle
     */
    @PostMapping("/{driverId}/vehicles")
    public Driver linkVehicleToDriver(@PathVariable Long driverId, @RequestParam String vehicleId) {
        return driverService.addVehicleToDriver(driverId, vehicleId);
    }

    /**
     * Get all vehicles associated with a driver by the driver's ID.
     *
     * @param driverId the ID of the driver whose vehicles are to be retrieved
     * @return ResponseEntity containing the list of vehicles and HTTP status
     */
    @GetMapping("/{driverId}/vehicles")
    public ResponseEntity<List<Vehicle>> getDriverVehicles(@PathVariable String driverId) {
        return driverService.getDriverVehicles(driverId);
    }
}
