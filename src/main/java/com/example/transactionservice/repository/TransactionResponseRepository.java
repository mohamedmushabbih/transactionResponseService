package com.example.transactionservice.repository;

import com.example.transactionservice.entity.TransactionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionResponseRepository extends JpaRepository<TransactionResponse, Long> {
}