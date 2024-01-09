package com.project.service;

import com.project.model.BankAccount;
import com.project.model.User;
import org.springframework.stereotype.Service;

@Service
public interface BankAccountService {

    public void addBankAccount(User user, BankAccount bankAccount);
}
