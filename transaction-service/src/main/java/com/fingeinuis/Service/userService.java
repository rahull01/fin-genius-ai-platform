package com.fingeinuis.Service;

import java.util.List;

import com.fingeinuis.dto.CreateTransactionRequest;
import com.fingeinuis.dto.transactionResponse;

public interface userService 
 
{
    transactionResponse createTransaction(CreateTransactionRequest request);

    List<transactionResponse> getTransactionsByUserId(Long userId);

}
