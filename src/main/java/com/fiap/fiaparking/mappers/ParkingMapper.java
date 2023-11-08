package com.fiap.fiaparking.mappers;

import com.fiap.fiaparking.dtos.ParkingSessionDTO;
import com.fiap.fiaparking.model.ParkingSession;
import org.mapstruct.Mapper;

@Mapper(componentModel = "string")
public interface ParkingMapper {

    ParkingSession toParkingSessionEntity(ParkingSessionDTO parkingSessionDTO);

    ParkingSessionDTO toParkingSessionDTO(ParkingSession parkingSession);
}
