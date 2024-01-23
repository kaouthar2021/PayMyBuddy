package com.project.service;

import com.project.dto.BankAccountDto;
import com.project.model.BankAccount;
import com.project.model.User;

import java.util.List;


public interface BankAccountService {

    public void addBankAccount(User user, BankAccountDto bankAccountDto);
    public List<BankAccount> findAllByUserId(int idUser);
    public void sendMoney(User user, String strIBANAccount, double amount);
    public void receiveMoney(User user, String strIBANAccount, double amount);
}
