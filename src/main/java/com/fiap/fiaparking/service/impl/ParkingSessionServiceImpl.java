package com.fiap.fiaparking.service.impl;

import com.fiap.fiaparking.model.ParkingSession;
import com.fiap.fiaparking.repository.ParkingSessionRepository;
import com.fiap.fiaparking.service.ParkingSessionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ParkingSessionServiceImpl implements ParkingSessionService {

    private final ParkingSessionRepository parkingSessionRepository;

    @Autowired
    public ParkingSessionServiceImpl(ParkingSessionRepository parkingSessionRepository) {
        this.parkingSessionRepository = parkingSessionRepository;
    }

    @Override
    public ParkingSession startParkingSession(ParkingSession parkingSession) {
        log.info("Starting parking session for vehicle ID: {}", parkingSession.getVehicle().getId());
        parkingSession.setStartTime(LocalDateTime.now());
        // Set other initial values for parkingSession if needed
        return parkingSessionRepository.save(parkingSession);
    }

    @Override
    public ParkingSession endParkingSession(Long sessionId) {
        log.info("Ending parking session with ID: {}", sessionId);
        ParkingSession session = parkingSessionRepository.findById(sessionId)
                .orElseThrow(() -> new EntityNotFoundException("Parking session not found with ID: " + sessionId));

        session.setEndTime(LocalDateTime.now());
        return parkingSessionRepository.save(session);
    }

    @Override
    public List<ParkingSession> getActiveSessionsByVehicle(Long vehicleId) {
        log.info("Retrieving active parking sessions for vehicle ID: {}", vehicleId);
        return parkingSessionRepository.findActiveSessionsByVehicleId(vehicleId);
    }
}
