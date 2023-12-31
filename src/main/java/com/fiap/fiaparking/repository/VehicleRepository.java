package com.fiap.fiaparking.repository;

import com.fiap.fiaparking.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
