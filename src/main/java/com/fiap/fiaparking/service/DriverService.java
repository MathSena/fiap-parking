package com.fiap.fiaparking.service;

import com.fiap.fiaparking.model.Driver;
import com.fiap.fiaparking.model.Vehicle;

import java.util.List;

public interface DriverService {
    Driver registerDriver(Driver driver);

    Driver findDriverByCpf(String cpf);

    List<Vehicle> getVehiclesByDriver(Long driverId);
}
