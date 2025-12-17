package com.finginuis.Ai_Advisory_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AiAdviceResponce
{
    private Long userId;
    private String question;
    private String advice;

}
