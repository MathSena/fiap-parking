package com.fiap.fiaparking.mappers;

import com.fiap.fiaparking.dtos.DriverDTO;
import com.fiap.fiaparking.model.Driver;
import org.mapstruct.Mapper;

@Mapper
public interface DriverMapper {

    Driver toDriverEntity(Driver driverDTO);

    DriverDTO toDriverDTO(Driver driver);
}
