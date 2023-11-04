package com.fiap.fiaparking.service.impl;

import com.fiap.fiaparking.enums.PaymentType;
import com.fiap.fiaparking.model.ParkingSession;
import com.fiap.fiaparking.model.PaymentDetails;
import com.fiap.fiaparking.repository.ParkingSessionRepository;
import com.fiap.fiaparking.repository.PaymentDetailsRepository;
import com.fiap.fiaparking.service.PaymentDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PaymentDetailsServiceImpl implements PaymentDetailsService {

    private final ParkingSessionRepository parkingSessionRepository;

    @Autowired
    public PaymentDetailsServiceImpl(ParkingSessionRepository parkingSessionRepository) {
        this.parkingSessionRepository = parkingSessionRepository;
    }


    @Override
    public void processPayment(ParkingSession session) {
        PaymentDetails paymentDetails = session.getPaymentDetails();
        double amount = session.getTotalCharge(); // Esta é a quantia calculada para a sessão de estacionamento.

        // Verifique se o tipo de pagamento é compatível com o tipo de estacionamento.
        paymentDetails.isPixAndFixedParkingCompatible(session.isFixed());

        switch (paymentDetails.getPaymentType()) {
            case CREDIT_CARD:
            case DEBIT:
            case PIX:
                // Registrar o pagamento
                session.setPaymentProcessed(true);
                log.info("Payment of {} for session {} processed using {}", amount, session.getId(), paymentDetails.getPaymentType());
                break;
            default:
                throw new UnsupportedOperationException("Payment type not supported");
        }

        // Supondo que você tenha um repositório para salvar a sessão
        parkingSessionRepository.save(session);
    }


}
