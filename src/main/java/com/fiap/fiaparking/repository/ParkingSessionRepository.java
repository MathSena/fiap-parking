package com.fiap.fiaparking.repository;

import com.fiap.fiaparking.model.ParkingSession;
import com.fiap.fiaparking.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.fiaparking.model.Driver;
import java.util.List;

@Repository
public interface ParkingSessionRepository extends JpaRepository<ParkingSession, Long> {
    List<ParkingSession> findByDriver(Driver driver);
    List<ParkingSession> findByVehicle(Vehicle vehicle);
    // Você pode querer buscar sessões ativas ou histórico de sessões
    List<ParkingSession> findByDriverAndIsActive(Driver driver, boolean isActive);
}
