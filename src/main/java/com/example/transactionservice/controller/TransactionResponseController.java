package com.example.transactionservice.controller;

import com.example.transactionservice.entity.TransactionResponse;
import com.example.transactionservice.service.TransactionResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction-responses")
public class TransactionResponseController {

    @Autowired
    private TransactionResponseService transactionResponseService;

    @GetMapping
    public List<TransactionResponse> getAllTransactionResponses() {
        return transactionResponseService.getAllTransactionResponses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getTransactionResponseById(@PathVariable Long id) {
        return transactionResponseService.getTransactionResponseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TransactionResponse createTransactionResponse(@RequestBody TransactionResponse transactionResponse) {
        return transactionResponseService.saveTransactionResponse(transactionResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponse> updateTransactionResponse(@PathVariable Long id, @RequestBody TransactionResponse transactionResponse) {
        return ResponseEntity.ok(transactionResponseService.updateTransactionResponse(id, transactionResponse));
    }
}