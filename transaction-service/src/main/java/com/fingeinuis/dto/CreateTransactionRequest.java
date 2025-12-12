package com.fingeinuis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Builder
public class CreateTransactionRequest
 
{
   private Long userId;
    private Double amount;
    private String type;
    private String category;
    private String description;
}
