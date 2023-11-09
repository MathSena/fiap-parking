package com.fiap.fiaparking.controller;


import com.fiap.fiaparking.model.ParkingSession;
import com.fiap.fiaparking.repository.ParkingSessionRepository;
import com.fiap.fiaparking.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/parking")
@RequiredArgsConstructor
public class ParkingSessionController {

    private final ParkingService parkingService;
    private final ParkingSessionRepository parkingRepository;

    @PostMapping
    public ResponseEntity<ParkingSession> createParking(@RequestBody ParkingSession parkingSession) {
        ParkingSession newParking = parkingService.createParking(parkingSession);
        return new ResponseEntity<>(newParking, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSession> getParking(@PathVariable String id) {
        Optional<ParkingSession> parkingOptional = parkingRepository.findById(Long.valueOf(id));
        return parkingOptional.map(parking -> new ResponseEntity<>(parking, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<ParkingSession>> listParkings() {
        List<ParkingSession> parkings = parkingRepository.findAll();
        return new ResponseEntity<>(parkings, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteParkings() {
        parkingRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingSession> updateParkingExit(
            @PathVariable String id, @RequestBody ParkingSession parkingSession) {
        return new ResponseEntity<>(parkingService.updateParkingExit(id, parkingSession), HttpStatus.OK);
    }


}
