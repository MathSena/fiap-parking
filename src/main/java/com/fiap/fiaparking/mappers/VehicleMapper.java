package com.fiap.fiaparking.mappers;

import com.fiap.fiaparking.dtos.VehicleDTO;
import com.fiap.fiaparking.model.Vehicle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "string")
public interface VehicleMapper {

    Vehicle toVehicleEntity(VehicleDTO vehicleDTO);

    VehicleDTO toVehicleDTO(Vehicle vehicle);
}
