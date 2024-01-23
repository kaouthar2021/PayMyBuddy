package com.project.service;

import com.project.dto.TransactionDto;
import com.project.model.Transaction;
import org.springframework.security.core.Authentication;

public interface TransactionService {
    public Transaction addTransaction(TransactionDto transactionDto , Authentication auth);
//    public Page<Transaction> getAllTransactionsSorted(Integer userId, Pageable pageable);
}
