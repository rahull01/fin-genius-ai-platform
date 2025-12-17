package com.finginuis.Ai_Advisory_service.service.implementation;

import com.finginuis.Ai_Advisory_service.dto.AiAdviceRequest;
import com.finginuis.Ai_Advisory_service.dto.AiAdviceResponce;
import com.finginuis.Ai_Advisory_service.dto.TransactionDto;
import com.finginuis.Ai_Advisory_service.service.AiAdivosyService;
import dev.langchain4j.model.chat.ChatLanguageModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AiAdvisoryServiceImple implements AiAdivosyService
{
    private final ChatLanguageModel chatLanguageModel;
    private final RestTemplate restTemplate;
    @Override
    public AiAdviceResponce getAdvice(AiAdviceRequest request)
    {
        String url ="http://API-GATEWAY/transaction-service/api/transactions/user/" +request.getUserId();

        TransactionDto[] txArray = restTemplate.getForObject(url,TransactionDto[].class);
        List<TransactionDto> transaction = txArray != null ? Arrays.asList(txArray) : List.of();

        log.info("Fetched {} transactions for user {}" , transaction.size() , request.getUserId());

        String txContect = transaction.stream()
                .map(t -> String.format(

                                "[%s] %s: %s ₹%.2f (%s)",
                                t.getTimestamp(),
                                t.getType(),
                                t.getCategory(),
                                t.getAmount(),
                                t.getDescription() != null ? t.getDescription() : "NA"


                ))
                .collect(Collectors.joining("\n"));

        if(txContect.isEmpty())
        {
            txContect = "No transaction found for this user. ";
        }

        String prompt = """
                You are a highly skilled personal finance advisor.
                              You speak in simple Hinglish (mix of Hindi and English),
                              and you give practical, India-focused advice.
              
                              Below is the transaction history of a user:
              
                              %s
              
                              The user is asking: "%s"
              
                              Based on the above transactions, give:
                              - Expense analysis (short but clear)
                              - Category-wise insights
                              - Saving suggestions
                              - Investment or budgeting tips (if possible)
              
                              Answer in 3-6 short paragraphs, with bullet points where useful.
                              Do NOT hallucinate transactions that are not in the list.
                              """.formatted(txContect,request.getQuestion());

        String answer = chatLanguageModel.generate(prompt);

        return   AiAdviceResponce.builder()
                .userId(request.getUserId())
                .question(request.getQuestion())
                .advice(answer)
                .build();
    }
}
