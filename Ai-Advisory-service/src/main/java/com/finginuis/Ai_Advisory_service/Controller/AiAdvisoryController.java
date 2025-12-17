package com.finginuis.Ai_Advisory_service.Controller;

import com.finginuis.Ai_Advisory_service.dto.AiAdviceRequest;
import com.finginuis.Ai_Advisory_service.dto.AiAdviceResponce;
import com.finginuis.Ai_Advisory_service.service.AiAdivosyService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiAdvisoryController
{
    private final AiAdivosyService service;

    @PostMapping("/advice")
    public ResponseEntity<AiAdviceResponce> getAdvice(@RequestBody AiAdviceRequest request)
    {
        return ResponseEntity.ok(service.getAdvice(request));
    }

}
