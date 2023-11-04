package com.fiap.fiaparking.service;

import com.fiap.fiaparking.model.ParkingSession;


public interface PaymentDetailsService {

    void processPayment(ParkingSession session);

}
