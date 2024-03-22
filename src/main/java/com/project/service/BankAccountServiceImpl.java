package com.project.service;

import com.project.model.BankAccount;
import com.project.model.Transaction;
import com.project.model.User;
import com.project.repository.BankAccountRepository;
import com.project.repository.TransactionRepository;
import com.project.repository.UserRepository;
import com.project.utils.Constante;
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

    public void addBankAccount(User user, BankAccount bankAccount) {
        BankAccount newBankAccount = new BankAccount();

        newBankAccount.setIban(bankAccount.getIban());
        newBankAccount.setDescription(bankAccount.getDescription());
        user.setBankAccount(newBankAccount);
        userRepository.save(user);
    }


    public List<BankAccount> findAllByUserId(int idUser) {
        return bankAccountRepository.findAllByUser_id(idUser);
    }

    @Transactional
    public void transferToOrFromMyBankAccount(User user, String strIBANAccount, double amount) {

        Double accountBalance;
        Transaction transaction = new Transaction();
        transaction.setUserReceiver(user);
        transaction.setUserSender(user);
        if (amount >= 0.0) {
            transaction.setDescription("Transfer from my IBAN Account " + strIBANAccount);
            accountBalance = amount;
        }
        else {
            transaction.setDescription(("Transfer to my IBAN Account " + strIBANAccount));
            accountBalance =  Math.round(amount * (1 + Constante.COMMISSION) * 100.00) / 100.00;
        }
        transaction.setAmount(amount);
        transactionRepository.save(transaction);
        user.setSolde(user.getSolde() + accountBalance);
    }
}

