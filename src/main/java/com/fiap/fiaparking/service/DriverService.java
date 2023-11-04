package com.fiap.fiaparking.service;

import com.fiap.fiaparking.dtos.DriverDTO;
import com.fiap.fiaparking.model.Driver;

public interface DriverService {
    Driver registerDriver(DriverDTO driver);
    Driver findDriverById(Long id);
    Driver updateDriver(Long id, DriverDTO driver);
    void deleteDriver(Long id);
    Driver findDriverVehicles(Long id);
    Driver findDriverVehicleById(Long id, Long vehicleId);


    Driver registerDriverVehicle(Long id, DriverDTO driverDTO);

    Driver updateDriverVehicle(Long id, Long vehicleId, DriverDTO driverDTO);

    Driver deleteDriverVehicle(Long id, Long vehicleId);

    Iterable<Driver> findAllDrivers();
}
