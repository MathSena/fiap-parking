package com.fiap.fiaparking.service.impl;


import com.fiap.fiaparking.dtos.VehicleDTO;
import com.fiap.fiaparking.model.Vehicle;
import com.fiap.fiaparking.service.VehicleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {


    @Override
    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {
        return null;
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) {
        return null;
    }

    @Override
    public VehicleDTO getVehicleById(Long id) {
        return null;
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return null;
    }
}
