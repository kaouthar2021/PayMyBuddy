package com.project.service;

import com.project.model.BankAccount;
import com.project.model.User;

import java.util.List;


public interface BankAccountService {

    public void addBankAccount(User user, BankAccount bankAccount);
    public List<BankAccount> findAllByUserId(int idUser);
    public void transferToOrFromMyBankAccount(User user, String strIBANAccount, double amount);
}
