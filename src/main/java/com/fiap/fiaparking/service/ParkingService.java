package com.fiap.fiaparking.service;


import com.fiap.fiaparking.model.ParkingSession;

import java.util.List;


public interface ParkingService {

    ParkingSession createParking(ParkingSession parkingSessionDTO);

    List<ParkingSession> findParkingsWithExpiringTime();

    ParkingSession updateParkingExit(Long id, ParkingSession parkingSession);

    void calculateValue(ParkingSession parking);
}
