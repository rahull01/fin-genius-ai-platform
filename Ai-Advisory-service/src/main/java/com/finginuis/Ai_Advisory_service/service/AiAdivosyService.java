package com.finginuis.Ai_Advisory_service.service;

import com.finginuis.Ai_Advisory_service.dto.AiAdviceRequest;
import com.finginuis.Ai_Advisory_service.dto.AiAdviceResponce;

public interface AiAdivosyService
{
    AiAdviceResponce getAdvice(AiAdviceRequest request);

}
