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

/**
 * Rest controller for managing parking sessions.
 */
@RestController
@RequestMapping("api/parking")
@RequiredArgsConstructor
public class ParkingSessionController {

    private final ParkingService parkingService;
    private final ParkingSessionRepository parkingRepository;

    /**
     * POST /api/parking : Create a new parking session.
     *
     * @param parkingSession the parking session to create.
     * @return the ResponseEntity with status 201 (Created) and with body the new parking session.
     */
    @PostMapping
    public ResponseEntity<ParkingSession> createParking(@RequestBody ParkingSession parkingSession) {
        ParkingSession newParking = parkingService.createParking(parkingSession);
        return new ResponseEntity<>(newParking, HttpStatus.CREATED);
    }

    /**
     * GET /api/parking/{id} : Get the parking session with the specified ID.
     *
     * @param id the ID of the parking session to retrieve.
     * @return the ResponseEntity with status 200 (OK) and with body the parking session, or with status 404 (Not Found) if the session does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ParkingSession> getParking(@PathVariable String id) {
        Optional<ParkingSession> parkingOptional = parkingRepository.findById(Long.valueOf(id));
        return parkingOptional.map(parking -> new ResponseEntity<>(parking, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * GET /api/parking : List all parking sessions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of all parking sessions.
     */
    @GetMapping
    public ResponseEntity<List<ParkingSession>> listParkings() {
        List<ParkingSession> parkings = parkingRepository.findAll();
        return new ResponseEntity<>(parkings, HttpStatus.OK);
    }

    /**
     * DELETE /api/parking : Delete all parking sessions.
     *
     * @return the ResponseEntity with status 204 (No Content).
     */
    @DeleteMapping()
    public ResponseEntity<Void> deleteParkings() {
        parkingRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * PUT /api/parking/{id} : Update the exit time of the parking session with the specified ID.
     *
     * @param id the ID of the parking session to update.
     * @param parkingSession the parking session with updated information.
     * @return the ResponseEntity with status 200 (OK) and with body the updated parking session.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ParkingSession> updateParkingExit(
            @PathVariable Long id, @RequestBody ParkingSession parkingSession) {
        return new ResponseEntity<>(parkingService.updateParkingExit(id, parkingSession), HttpStatus.OK);
    }
}
