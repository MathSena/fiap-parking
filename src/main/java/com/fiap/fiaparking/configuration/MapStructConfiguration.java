package com.fiap.fiaparking.configuration;

import com.fiap.fiaparking.mappers.DriverMapperImpl;
import com.fiap.fiaparking.mappers.ParkingMapperImpl;
import com.fiap.fiaparking.mappers.PaymentMapperImpl;
import com.fiap.fiaparking.mappers.VehicleMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapStructConfiguration {

    @Bean
    public DriverMapperImpl getDriverMapper(){
        return new DriverMapperImpl();
    }

    @Bean
    public ParkingMapperImpl getParkingMapper(){
        return new ParkingMapperImpl();
    }

    @Bean
    public PaymentMapperImpl getPaymentMapper(){
        return new PaymentMapperImpl();
    }

    @Bean
    public VehicleMapperImpl getVeicleMapper(){
        return new VehicleMapperImpl();
    }
}
