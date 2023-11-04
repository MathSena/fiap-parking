package com.fiap.fiaparking.repository;

import com.fiap.fiaparking.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    @Query("SELECT d FROM Driver d JOIN d.vehicles v WHERE v.id = :vehicleId")
    Optional<Driver> findDriverByVehicleId(@Param("vehicleId") Long vehicleId);
}


