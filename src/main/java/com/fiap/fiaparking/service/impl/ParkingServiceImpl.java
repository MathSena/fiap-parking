package com.fiap.fiaparking.service.impl;


import com.fiap.fiaparking.dtos.ParkingSessionDTO;
import com.fiap.fiaparking.enums.DurationType;
import com.fiap.fiaparking.mappers.ParkingMapper;
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
    private final NotificationService notificationService;
    private final ParkingMapper parkingMapper;

    public ParkingSession createParking(ParkingSessionDTO parkingSessionDTO) {

        String driverId = String.valueOf(parkingSessionDTO.getDriverId());
        Optional<Driver> isDriver = driverRepository.findById(Long.valueOf(driverId));

        if (isDriver.isEmpty()) {
            throw new DataIntegrityViolationException("Driver with ID not found: " + driverId);
        }

        String vehicleId = String.valueOf(parkingSessionDTO.getVehicleId());
        Optional<Vehicle> isVehicle = vehicleRepository.findById(Long.valueOf(vehicleId));

        if (isVehicle.isEmpty()) {
            throw new DataIntegrityViolationException("Vehicle with ID not found: " + vehicleId);
        }

        if (isDriver.get()
                .getVehicles()
                .stream()
                .noneMatch(vehicle -> vehicle.getLicensePlate()
                        .equals(vehicleId))) {
            String errorMessage = "The vehicle is not associated with the driver. Vehicle ID: " + vehicleId + ", Driver ID: " + driverId;
            throw new DataIntegrityViolationException(errorMessage);
        }

        ParkingSession parking = parkingMapper.toParkingSessionEntity(parkingSessionDTO);

        if (parkingSessionDTO.getType() == DurationType.FIXED) {
            // informing exit is mandatory
            if (parkingSessionDTO.getExit() == null) {
                throw new DataIntegrityViolationException("Informing the exit is mandatory for fixed time.");
            }
        } else {
            if (parkingSessionDTO.getExit() != null) {
                throw new DataIntegrityViolationException("Exit can only be informed when parking type is FIXED_PERIOD");
            }
        }

        parking.setDriver(isDriver.get());
        parking.setVehicle(isVehicle.get());

        ParkingSession newParking = parkingRepository.save(parking);

        notificationService.sendNotification("Session Parking has been created.");

        return newParking;
    }

    public List<ParkingSession> findParkingsWithExpiringTime() {
        LocalDateTime timeMinutesBefore = LocalDateTime.now()
                .minusMinutes(5L);
        return parkingRepository.findAllByExitIsNullAndEntryLessThanEqual(timeMinutesBefore);
    }

    public ParkingSession updateParkingExit(String id, ParkingSessionDTO parkingSessionDTO) {
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
