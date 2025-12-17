package com.finginuis.Ai_Advisory_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionDto
{
    private Long id;
    private Long userId;
    private Double amount;
    private String type;
    private String category;
    private String description;
    private LocalDateTime timestamp;

}
