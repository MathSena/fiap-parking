package com.fiap.fiaparking.service.impl;
import com.fiap.fiaparking.model.Driver;
import com.fiap.fiaparking.model.Vehicle;
import com.fiap.fiaparking.repository.DriverRepository;
import com.fiap.fiaparking.repository.VehicleRepository;
import com.fiap.fiaparking.service.DriverService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository, VehicleRepository vehicleRepository) {
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Driver registerDriver(Driver driver) {
        log.info("Registering driver with CPF: {}", driver.getCpf());
        return driverRepository.save(driver);
    }

    @Override
    public Driver findDriverByCpf(String cpf) {
        log.info("Searching for driver with CPF: {}", cpf);
        return driverRepository.findByCpf(cpf)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with CPF: " + cpf));
    }

    @Override
    public List<Vehicle> getVehiclesByDriver(Long driverId) {
        log.info("Retrieving vehicles for driver ID: {}", driverId);
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with ID: " + driverId));
        return vehicleRepository.findByDriver(driver);
    }
}