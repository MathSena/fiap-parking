package com.fiap.fiaparking.service.impl;


import com.fiap.fiaparking.enums.DurationType;
import com.fiap.fiaparking.model.Driver;
import com.fiap.fiaparking.model.ParkingSession;
import com.fiap.fiaparking.model.Payment;
import com.fiap.fiaparking.model.Vehicle;
import com.fiap.fiaparking.repository.DriverRepository;
import com.fiap.fiaparking.repository.ParkingSessionRepository;
import com.fiap.fiaparking.repository.VehicleRepository;
import com.fiap.fiaparking.service.NotificationService;
import com.fiap.fiaparking.service.ParkingService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ParkingServiceImpl implements ParkingService {
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;
    private final ParkingSessionRepository parkingRepository;



    public ParkingSession createParking(ParkingSession parkingSession) {

        Long driverId = parkingSession.getDriver().getId();
        Optional<Driver> isDriver = driverRepository.findById(driverId);

        if (isDriver.isEmpty()) {
            throw new DataIntegrityViolationException("Driver with ID not found: " + driverId);
        }

        Long vehicleId = parkingSession.getVehicle().getId();
        Optional<Vehicle> isVehicle = vehicleRepository.findById(vehicleId);

        if (isVehicle.isEmpty()) {
            throw new DataIntegrityViolationException("Vehicle with ID not found: " + vehicleId);
        }

        parkingSession.setDriver(isDriver.get());
        parkingSession.setVehicle(isVehicle.get());
        parkingSession.setType(DurationType.FIXED);
        parkingSession.setEntry(LocalDateTime.now());
        parkingSession.setExit(null);
        parkingSession.setValue(0.0);
        parkingSession.setPayment(null);
        return parkingRepository.save(parkingSession);

    }

    public List<ParkingSession> findParkingsWithExpiringTime() {
        LocalDateTime timeMinutesBefore = LocalDateTime.now()
                .minusMinutes(5L);
        return parkingRepository.findAllByExitIsNullAndEntryLessThanEqual(timeMinutesBefore);
    }

    public ParkingSession updateParkingExit(String id, ParkingSession parkingSessionDTO) {
        ParkingSession parking = parkingRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new DataIntegrityViolationException("Parking not found with ID: "
                        + id));

        if (parking.getType() != DurationType.FIXED) {
            throw new DataIntegrityViolationException("Only parkings of type HOURLY can have their " +
                    "exit time updated.");
        }

        parking.getDriver()
                .getPaymentMethod()
                .stream()
                .map(Payment::getId)
                .noneMatch(paymentMethodId -> false);
        throw new DataIntegrityViolationException("Payment method not found for the driver.");

    }

    public void calculateValue(ParkingSession parking) {
        if (parking.getEntry() != null && parking.getExit() != null) {
            long minutesParked = Duration.between(parking.getEntry(), parking.getExit())
                    .toMinutes();
            double hoursParked = minutesParked / 60.0;

            int fullHours = (int) Math.ceil(hoursParked);

            parking.setValue(fullHours * 10);
        }
    }
}
