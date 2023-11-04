package com.fiap.fiaparking.repository;

import com.fiap.fiaparking.model.ParkingSession;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ParkingSessionRepository extends JpaRepository<ParkingSession, Long> {
    @Query("SELECT ps FROM ParkingSession ps WHERE ps.endTime is null AND (ps.startTime <= :thresholdFixed AND ps.isFixed = true) OR (ps.isFixed = false)")
    List<ParkingSession> findSessionsToAlert(LocalDateTime thresholdFixed);


    List<ParkingSession> findActiveSessionsByVehicleId(Long vehicleId);
}
