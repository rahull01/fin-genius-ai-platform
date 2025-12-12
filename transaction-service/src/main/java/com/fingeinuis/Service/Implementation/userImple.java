package com.fingeinuis.Service.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fingeinuis.Service.userService;
import com.fingeinuis.dto.CreateTransactionRequest;
import com.fingeinuis.dto.transactionResponse;
import com.fingeinuis.entity.transaction;
import com.fingeinuis.kafka.TransactionEvenProducer;
import com.fingeinuis.repositort.transactionRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class userImple implements userService
{
    private final transactionRepo transactionRepo;
    private final TransactionEvenProducer transactionEvenProducer;

    @Override
    public transactionResponse createTransaction(CreateTransactionRequest request) {

        
        transaction tx = transaction.builder()
                .userId(request.getUserId())
                .amount(request.getAmount())
                .type(request.getType())
                .category(request.getCategory())
                .description(request.getDescription())
                .build();

        transaction saved = transactionRepo.save(tx);

        transactionEvenProducer.sendTransactionEvent(saved);

        return Map(saved);
    }

    @Override
    public List<transactionResponse> getTransactionsByUserId(Long userId)
     { 
        return transactionRepo.findByUserId(userId)
                .stream()
                .map(this::Map)
                .toList();
    }

    private transactionResponse Map(transaction tx)
    {
        return transactionResponse.builder()
                .id(tx.getId())
                .userId(tx.getUserId())
                .amount(tx.getAmount())
                .type(tx.getType())
                .category(tx.getCategory())
                .description(tx.getDescription())
                .timestamp(tx.getTimestamp().toString())
                .build();
    }
}
