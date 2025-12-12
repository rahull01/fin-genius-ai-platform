package com.fingeinuis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fingeinuis.Service.userService;
import com.fingeinuis.dto.CreateTransactionRequest;
import com.fingeinuis.dto.transactionResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController
 {
    @Autowired
    private final userService userService;

    @PostMapping
    public ResponseEntity<transactionResponse> create(@RequestBody CreateTransactionRequest request) {
        transactionResponse response = userService.createTransaction(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List <transactionResponse>> getByUser(@PathVariable Long userId)
    {
       return ResponseEntity.ok(userService.getTransactionsByUserId(userId));
    }



}
