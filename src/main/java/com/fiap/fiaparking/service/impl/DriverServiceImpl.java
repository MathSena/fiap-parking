package com.fiap.fiaparking.service.impl;

import com.fiap.fiaparking.dtos.DriverDTO;
import com.fiap.fiaparking.dtos.PaymentDetailsDTO;
import com.fiap.fiaparking.dtos.VehicleDTO;
import com.fiap.fiaparking.enums.PaymentType;
import com.fiap.fiaparking.model.Driver;
import com.fiap.fiaparking.model.PaymentDetails;
import com.fiap.fiaparking.model.Vehicle;
import com.fiap.fiaparking.repository.DriverRepository;
import com.fiap.fiaparking.service.DriverService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Transactional
    public Driver registerDriver(DriverDTO driverDTO) {
        Driver driver = new Driver();
        driver.setName(driverDTO.getName());
        driver.setCpf(driverDTO.getCpf());
        driver.setAddress(driverDTO.getAddress());
        driver.setEmail(driverDTO.getEmail());

        // Converter e definir os detalhes de pagamento se eles forem fornecidos
        if (driverDTO.getPaymentDetails() != null && driverDTO.getPaymentDetails().getPaymentType() != PaymentType.PIX) {
            PaymentDetails paymentDetails = convertToEntity(driverDTO.getPaymentDetails());
            driver.setPaymentDetails(paymentDetails);
        }else{
            log.info("Payment with PIX is not available for this type of user at the time of registration.");
        }

        // Converter e adicionar a lista de veículos
        List<Vehicle> vehicleList = driverDTO.getVehicles()
                .stream()
                .map(vehicleDTO -> convertToEntity(vehicleDTO, driver))
                .collect(Collectors.toList());
        driver.setVehicles(vehicleList);

        // Salvar o motorista no banco de dados
        return driverRepository.save(driver);
    }

    public Driver findDriverById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with ID: " + id));
    }

    public Driver updateDriver(Long id, DriverDTO driverDTO) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with ID: " + id));
        driver.setName(driverDTO.getName());
        driver.setCpf(driverDTO.getCpf());
        driver.setAddress(driverDTO.getAddress());
        driver.setEmail(driverDTO.getEmail());
        return driverRepository.save(driver);
    }

    public void deleteDriver(Long id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with ID: " + id));
        driverRepository.delete(driver);
    }

    public Driver findDriverVehicles(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with ID: " + id));
    }

    public Driver findDriverVehicleById(Long id, Long vehicleId) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with ID: " + id));
    }

    public Driver registerDriverVehicle(Long id, DriverDTO driverDTO) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with ID: " + id));
    }

    public Driver updateDriverVehicle(Long id, Long vehicleId, DriverDTO driverDTO) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with ID: " + id));
    }


    public Driver deleteDriverVehicle(Long id, Long vehicleId) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with ID: " + id));
    }

    public Iterable<Driver> findAllDrivers() {
        return driverRepository.findAll();
    }

    private Vehicle convertToEntity(VehicleDTO vehicleDTO, Driver driver) {
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate(vehicleDTO.getLicensePlate());
        vehicle.setMake(vehicleDTO.getMake());
        vehicle.setModel(vehicleDTO.getModel());
        // Note que aqui você precisa configurar o driver, como está fazendo no código original
        vehicle.setDriver(driver);
        return vehicle;
    }

    private PaymentDetails convertToEntity(PaymentDetailsDTO paymentDetailsDTO) {
        PaymentDetails paymentDetails = new PaymentDetails();
        // Supondo que você tenha métodos setters correspondentes na entidade PaymentDetails
        paymentDetails.setPaymentType(paymentDetailsDTO.getPaymentType());
        paymentDetails.setPaymentInfo(paymentDetailsDTO.getPaymentInfo());
        // Configurar outros campos conforme necessário
        return paymentDetails;
    }

}

