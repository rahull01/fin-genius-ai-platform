package com.finginuis.Ai_Advisory_service.dto;

import lombok.Data;

@Data
public class AiAdviceRequest
{
    private Long userId;
    private String question;
}
