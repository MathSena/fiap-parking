package com.fiap.fiaparking.repository;

import com.fiap.fiaparking.model.ParkingSession;
import com.fiap.fiaparking.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    List<Receipt> findByParkingSession(ParkingSession parkingSession);
}