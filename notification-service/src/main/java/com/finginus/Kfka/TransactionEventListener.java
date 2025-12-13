package com.finginus.Kfka;


import java.util.Map;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.finginus.Service.NotificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionEventListener 
{
    private final NotificationService notificationService;

    @KafkaListener(topics = "transaction-events", groupId = "notification-group")
    public void consume(Map<String, Object> event)
    {
        log.info("Received transaction event: {}", event);
        Long userId = Long.valueOf(event.get("userId").toString());
        double amount = Double.parseDouble(event.get("amount").toString());
        String type = event.get("type").toString().trim();
        String category = event.get("category").toString().trim();



        notificationService.processTransactionEvent(userId, amount, type, category);
    }

}
