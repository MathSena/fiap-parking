package com.fiap.fiaparking.service.impl;

import com.fiap.fiaparking.dtos.ParkingSessionDTO;
import com.fiap.fiaparking.enums.PaymentType;
import com.fiap.fiaparking.model.Driver;
import com.fiap.fiaparking.model.ParkingSession;
import com.fiap.fiaparking.model.PaymentDetails;
import com.fiap.fiaparking.repository.DriverRepository;
import com.fiap.fiaparking.repository.ParkingSessionRepository;
import com.fiap.fiaparking.service.ParkingSessionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ParkingSessionServiceImpl implements ParkingSessionService {

    private final ParkingSessionRepository parkingSessionRepository;
    private final DriverRepository driverRepository;

    private final static double HOURLY_RATE = 5.0; // suponha que a taxa seja de R$ 5,00 por hora
    private final static double FIXED_RATE = 20.0;

    @Autowired
    public ParkingSessionServiceImpl(ParkingSessionRepository parkingSessionRepository, DriverRepository driverRepository) {
        this.parkingSessionRepository = parkingSessionRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public ParkingSession startParkingSession(ParkingSessionDTO parkingDTO) {
        log.info("Starting parking session for vehicle ID: {}", parkingDTO.getVehicleId());

        Driver driver = driverRepository.findDriverByVehicleId(parkingDTO.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Driver not found for vehicle ID: " + parkingDTO.getVehicleId()));

        validatePaymentOptionForParkingType(driver.getPaymentDetails(), parkingDTO.isFixed());

        ParkingSession parkingSession = new ParkingSession();
        parkingSession.setVehicle(driver.getVehicleById(parkingDTO.getVehicleId())); // Ajustar conforme a implementação
        parkingSession.setStartTime(parkingDTO.getStartTime());
        parkingSession.setFixed(parkingDTO.isFixed());
        parkingSession.setPaymentDetails(driver.getPaymentDetails());

        return parkingSessionRepository.save(parkingSession);
    }

    @Override
    public ParkingSession endParkingSession(Long sessionId) {
        ParkingSession session = parkingSessionRepository.findById(sessionId)
                .orElseThrow(() -> new EntityNotFoundException("Parking session not found with ID: " + sessionId));

        LocalDateTime endTime = LocalDateTime.now();
        session.setEndTime(endTime);

        double cost = session.isFixed() ? FIXED_RATE : calculateVariableParkingCost(session.getStartTime(), endTime);
        session.setTotalCharge(cost);

        // Processamento adicional para pagamento (se necessário)

        return parkingSessionRepository.save(session);
    }

    @Override
    public List<ParkingSession> getActiveSessionsByVehicle(Long vehicleId) {
        log.info("Retrieving active parking sessions for vehicle ID: {}", vehicleId);
        return parkingSessionRepository.findActiveSessionsByVehicleId(vehicleId);
    }

    private void validatePaymentOptionForParkingType(PaymentDetails paymentDetails, boolean isFixed) {
        if (paymentDetails.getPaymentType() == PaymentType.PIX && !isFixed) {
            throw new UnsupportedOperationException("PIX can only be used for fixed parking periods.");
        }
    }

    private Double calculateParkingCost(LocalDateTime startTime, LocalDateTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        long hours = duration.toHours();
        return (double) (HOURLY_RATE * (hours + (duration.toMinutesPart() > 0 ? 1 : 0))); // Adiciona uma hora se houver fração
    }

    // Este método é um placeholder para a implementação da lógica de alertas.
    // Deve ser executado periodicamente (e.g., a cada minuto)
    @Scheduled(fixedDelay = 60000)
    public void checkAndSendAlerts() {
        // Definir o limiar de tempo para alerta, por exemplo, 15 minutos antes do tempo expirar
        LocalDateTime thresholdFixed = LocalDateTime.now()
                .plusMinutes(15);

        List<ParkingSession> sessionsToAlert = parkingSessionRepository.findSessionsToAlert(thresholdFixed);
        for (ParkingSession session : sessionsToAlert) {
            if (session.isFixed()) {
                sendFixedTimeAlert(session);
            } else {
                extendVariableTimeSession(session);
            }
        }
    }

    private void sendFixedTimeAlert(ParkingSession session) {
        // Implementar lógica para enviar alerta ao condutor
        // sobre o término do período de estacionamento fixo
    }

    private void extendVariableTimeSession(ParkingSession session) {
        // Implementar lógica para estender automaticamente o período de estacionamento
        // e notificar o condutor, a menos que ele tenha optado por não estender
    }

    private double calculateVariableParkingCost(LocalDateTime startTime, LocalDateTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        long hours = duration.toHours();
        return HOURLY_RATE * (hours + (duration.toMinutesPart() > 0 ? 1 : 0)); // Cobrança por hora completa, se houver fração de hora, cobrar uma hora extra
    }
}

