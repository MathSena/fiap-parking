package com.fiap.fiaparking.service.impl;

import com.fiap.fiaparking.model.ParkingSession;
import com.fiap.fiaparking.model.Receipt;
import com.fiap.fiaparking.repository.ReceiptRepository;
import com.fiap.fiaparking.service.ReceiptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;

    @Autowired
    public ReceiptServiceImpl(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    @Override
    public Receipt generateReceipt(ParkingSession parkingSession) {
        log.info("Generating receipt for parking session ID: {}", parkingSession.getId());
        Receipt receipt = new Receipt();
        // Assume we have some business logic to calculate the cost based on the parking session details
        double cost = calculateParkingCost(parkingSession);
        receipt.setAmount(cost);
        receipt.setParkingSession(parkingSession);
        receipt.setIssueDate(LocalDateTime.now());
        // Set other receipt details if necessary

        return receiptRepository.save(receipt);
    }

    private double calculateParkingCost(ParkingSession parkingSession) {
        // Implement the cost calculation logic
        // This is just a placeholder for demonstration purposes
        return 0.0;
    }
}