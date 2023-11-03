package com.fiap.fiaparking.service.impl;

import com.fiap.fiaparking.model.Driver;
import com.fiap.fiaparking.model.Vehicle;
import com.fiap.fiaparking.repository.VehicleRepository;
import com.fiap.fiaparking.service.VehicleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle registerVehicle(Vehicle vehicle) {
        log.info("Registering vehicle with plate number: {}", vehicle.getLicensePlate());
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Driver findVehicleById(Long id) {
        log.info("Searching for vehicle with ID: {}", id);
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with ID: " + id));
    }
}