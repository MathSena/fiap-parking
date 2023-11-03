package com.fiap.fiaparking.repository;

import com.fiap.fiaparking.model.Driver;
import com.fiap.fiaparking.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VehicleRepository extends JpaRepository<Driver, Long> {

    List<Vehicle> findByDriver(Driver driver);

    Vehicle save(Vehicle vehicle);
}
