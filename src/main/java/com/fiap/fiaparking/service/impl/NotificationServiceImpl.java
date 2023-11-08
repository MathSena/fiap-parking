package com.fiap.fiaparking.service.impl;

import com.fiap.fiaparking.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    @Async
    @Override
    public void sendNotification(String payload) {
        log.info("Sending notification. Message - {}", payload);
    }

}