package com.example.transactionservice.service;

import com.example.transactionservice.entity.TransactionResponse;
import com.example.transactionservice.repository.TransactionResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionResponseService {

    @Autowired
    private TransactionResponseRepository transactionResponseRepository;

    @Cacheable(value = "transactionResponses")
    public List<TransactionResponse> getAllTransactionResponses() {
        return transactionResponseRepository.findAll();
    }

    @Cacheable(value = "transactionResponse", key = "#id")
    public Optional<TransactionResponse> getTransactionResponseById(Long id) {
        return transactionResponseRepository.findById(id);
    }

    @CacheEvict(value = "transactionResponses", allEntries = true)
    public TransactionResponse saveTransactionResponse(TransactionResponse transactionResponse) {
        return transactionResponseRepository.save(transactionResponse);
    }

    @CacheEvict(value = "transactionResponse", key = "#id")
    @CacheEvict(value = "transactionResponses", allEntries = true)
    public TransactionResponse updateTransactionResponse(Long id, TransactionResponse transactionResponseDetails) {
        TransactionResponse transactionResponse = transactionResponseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TransactionResponse not found with id " + id));

        transactionResponse.setTransactionId(transactionResponseDetails.getTransactionId());
        transactionResponse.setStatus(transactionResponseDetails.getStatus());
        transactionResponse.setMessage(transactionResponseDetails.getMessage());

        return transactionResponseRepository.save(transactionResponse);
    }
}