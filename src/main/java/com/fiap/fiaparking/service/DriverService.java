package com.fiap.fiaparking.service;

import com.fiap.fiaparking.model.Driver;
import com.fiap.fiaparking.model.Vehicle;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DriverService {

    Driver createDriver(Driver driverDTO);

    Driver updateDriver(Driver driver);

    Driver getDriverById(Long id);

    List<Driver> getAllDrivers();

    Driver addVehicleToDriver(Long id, String vehicleId);

    ResponseEntity<List<Vehicle>> getDriverVehicles(String driverId);
}
