package com.fiap.fiaparking.service;

import com.fiap.fiaparking.model.ParkingSession;

import java.util.List;

public interface ParkingSessionService {
    ParkingSession startParkingSession(ParkingSession parkingSession);
    ParkingSession endParkingSession(Long sessionId);
    List<ParkingSession> getActiveSessionsByVehicle(Long vehicleId);
}