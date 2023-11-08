package com.fiap.fiaparking.mappers;

import com.fiap.fiaparking.model.Driver;
import org.mapstruct.Mapper;

@Mapper(componentModel = "string")
public interface DriverMapper {

    Driver toDriverEntity(Driver driverDTO);

}
