package com.fiap.fiaparking.service.impl;
import com.fiap.fiaparking.dtos.DriverDTO;
import com.fiap.fiaparking.model.Driver;
import com.fiap.fiaparking.model.Vehicle;
import com.fiap.fiaparking.repository.DriverRepository;
import com.fiap.fiaparking.service.DriverService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Transactional
    public Driver registerDriver(DriverDTO driverDTO) {
        Driver driver = new Driver();
        driver.setName(driverDTO.getName());
        driver.setCpf(driverDTO.getCpf());
        driver.setAddress(driverDTO.getAddress());
        driver.setEmail(driverDTO.getEmail());

        List<Vehicle> vehicleList = driverDTO.getVehicles().stream().map(vehicleDTO -> {
            Vehicle vehicle = new Vehicle();
            vehicle.setLicensePlate(vehicleDTO.getLicensePlate());
            vehicle.setMake(vehicleDTO.getMake());
            vehicle.setModel(vehicleDTO.getModel());
            vehicle.setDriver(driver); // associate the vehicle with the driver here
            return vehicle;
        }).collect(Collectors.toList());

        driver.setVehicles(vehicleList);

        return driverRepository.save(driver);
    }

    public Driver findDriverById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with ID: " + id));
    }

    public Driver updateDriver(Long id, DriverDTO driverDTO) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with ID: " + id));
        driver.setName(driverDTO.getName());
        driver.setCpf(driverDTO.getCpf());
        driver.setAddress(driverDTO.getAddress());
        driver.setEmail(driverDTO.getEmail());
        return driverRepository.save(driver);
    }

    public void deleteDriver(Long id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with ID: " + id));
        driverRepository.delete(driver);
    }

    public Driver findDriverVehicles(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with ID: " + id));
    }

    public Driver findDriverVehicleById(Long id, Long vehicleId) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with ID: " + id));
    }

    public Driver registerDriverVehicle(Long id, DriverDTO driverDTO) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with ID: " + id));
    }

    public Driver updateDriverVehicle(Long id, Long vehicleId, DriverDTO driverDTO) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with ID: " + id));
    }


    public Driver deleteDriverVehicle(Long id, Long vehicleId) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with ID: " + id));
    }

    public Iterable<Driver> findAllDrivers() {
        return driverRepository.findAll();
    }

}

