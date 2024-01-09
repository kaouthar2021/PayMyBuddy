package com.project.service;

import com.project.model.BankAccount;
import com.project.model.User;
import com.project.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    @Autowired
    BankAccountRepository bankAccountRepository;
    public void addBankAccount(User user, BankAccount bankAccount) {
        BankAccount newBankAccount = new BankAccount();
        newBankAccount.setUser(user);
        newBankAccount.setIban(bankAccount.getIban());
        newBankAccount.setDescription(bankAccount.getDescription());
        bankAccountRepository.save(newBankAccount);
    }

}
