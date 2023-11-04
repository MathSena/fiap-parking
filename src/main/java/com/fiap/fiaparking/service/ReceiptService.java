package com.fiap.fiaparking.service;

import com.fiap.fiaparking.model.ParkingSession;
import com.fiap.fiaparking.model.Receipt;

public interface ReceiptService {
    Receipt generateReceipt(ParkingSession parkingSession);
}