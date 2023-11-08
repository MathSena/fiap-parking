package com.fiap.fiaparking.service;

import com.fiap.fiaparking.dtos.ParkingSessionDTO;
import com.fiap.fiaparking.model.ParkingSession;

import java.util.List;


public interface ParkingService {

    ParkingSession createParking(ParkingSessionDTO parkingSessionDTO);

    List<ParkingSession> findParkingsWithExpiringTime();

    ParkingSession updateParkingExit(String id, ParkingSessionDTO parkingSessionDTO);

    void calculateValue(ParkingSession parking);
}
