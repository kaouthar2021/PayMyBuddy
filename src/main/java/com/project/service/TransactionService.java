package com.project.service;

import com.project.dto.TransactionDto;
import com.project.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface TransactionService {
    public Transaction addTransaction(TransactionDto transactionDto , Authentication auth);
    public Page<Transaction> findAllByEmitterId(final int idUser, Pageable pageableParam);
    public Page<Transaction> findAllByEmitterAndReceiverId(int idUser, Pageable pageableParam);
    Page<Transaction> findAllByUserSender_idOrUserReceiver_id(final int senderId,final int receiverId, Pageable pageableParam);
}
