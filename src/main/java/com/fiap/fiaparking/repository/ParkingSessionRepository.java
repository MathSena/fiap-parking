package com.fiap.fiaparking.repository;

import com.fiap.fiaparking.model.ParkingSession;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface ParkingSessionRepository extends JpaRepository<ParkingSession, Long> {

    List<ParkingSession> findAllByExitIsNullAndEntryLessThanEqual(LocalDateTime entry);
}
