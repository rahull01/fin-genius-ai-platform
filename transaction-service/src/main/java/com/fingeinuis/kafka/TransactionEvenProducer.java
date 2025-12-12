package com.fingeinuis.kafka;

import java.util.Map;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fingeinuis.entity.transaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionEvenProducer
 {
    private final KafkaTemplate<String , Object> kafkaTemplate;

    public void sendTransactionEvent(transaction tx)
    {
        Map<String , Object> event  = Map.of(
             "transactionId" , tx.getId(),
             "userId" , tx.getUserId(),
                "amount" , tx.getAmount(),
                "type" , tx.getType(),
                "category" , tx.getCategory(),
                "timestamp" , tx.getTimestamp().toString()
        );

          log.info("📤 Sending event to Kafka: {}", event);

        kafkaTemplate.send("transaction-events", event)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("✅ SUCCESS — Kafka ne message accept kiya. Metadata: {}",
                                result.getRecordMetadata());
                    } else {
                        log.error("❌ FAILED — Kafka ne message reject kar diya!", ex);
                    }
                });
    }
}
