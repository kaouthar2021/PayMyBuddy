package com.project.service;

import com.project.dto.BankAccountDto;
import com.project.model.BankAccount;
import com.project.model.Transaction;
import com.project.model.User;
import com.project.repository.BankAccountRepository;
import com.project.repository.TransactionRepository;
import com.project.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    @Autowired
    BankAccountRepository bankAccountRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;
    public void addBankAccount(User user, BankAccountDto bankAccountDto) {
        BankAccount newBankAccount = new BankAccount();
        newBankAccount.setUser(user);
        newBankAccount.setIban(bankAccountDto.getIban());
        newBankAccount.setDescription(bankAccountDto.getDescription());
        bankAccountRepository.save(newBankAccount);
    }
    public List<BankAccount> findAllByUserId(int idUser) {
        return bankAccountRepository.findAllByUser_id(idUser);
    }

    @Transactional
    public void sendMoney(User user, String iban, double amount) {

        Double accountBalance = amount * -1; // Le montant est négatif car c'est un envoi
        double commission = amount * 0.005 / 100;

        Transaction transaction = new Transaction();
        transaction.setUserReceiver(user);
        transaction.setUserSender(user);
        transaction.setDescription("Transfer to IBAN Account " + iban);
        transaction.setAmount(accountBalance + commission);

        transactionRepository.save(transaction);
        user.setSolde(user.getSolde() + (accountBalance + commission));
    }

    @Transactional
    public void receiveMoney(User user, String iban, double amount) {
        Double accountBalance = amount;

        Transaction transaction = new Transaction();
        transaction.setUserReceiver(user);
        transaction.setUserSender(user);
        transaction.setDescription("Transfer from IBAN Account " + iban);
        transaction.setAmount(accountBalance);

        transactionRepository.save(transaction);
        user.setSolde(user.getSolde() + accountBalance);
    }



}

