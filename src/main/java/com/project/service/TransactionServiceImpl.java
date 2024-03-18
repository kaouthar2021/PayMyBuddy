package com.project.service;

import com.project.dto.TransactionDto;
import com.project.model.Transaction;
import com.project.model.User;
import com.project.repository.TransactionRepository;
import com.project.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TransactionServiceImpl implements TransactionService {

    private  final TransactionRepository transactionRepository;

    private final  UserService userService;

    private final   UserRepository userRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository , UserService userService, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userService=userService;
        this.userRepository=userRepository;
    }

@Transactional
    public Transaction addTransaction(TransactionDto transactionDto ,Authentication auth){
        Double amount=transactionDto.getAmount();
        String connection=transactionDto.getConnection();
        String description=transactionDto.getDescription();
       double commission = amount * 0.005 / 100;
       double amountWithCommission = amount + commission;

       User userSender=userService.getCurrentUser(auth.getName());
       User userReceiver=userService.findUserByEmail(connection);
       if (userSender.getSolde() < amountWithCommission) {
           throw new RuntimeException("Not enough money on your account");
       }
       Transaction transaction=new Transaction();
       transaction.setAmount(amount);
       transaction.setTransaction_date(new Date());
       transaction.setUserSender(userSender);
       transaction.setUserReceiver(userReceiver);
       transaction.setDescription(description);

       transactionRepository.save(transaction);

       userSender.setSolde(userSender.getSolde() - amountWithCommission);
       userReceiver.setSolde(userReceiver.getSolde() + amount);

       userService.saveUser(userSender);
       userService.saveUser(userReceiver);
       return transaction;
   }

    public Page<Transaction> findAllByEmitterId(final int idUser, Pageable pageableParam) {
        return transactionRepository.findAllByUserSender_id(idUser, pageableParam);
    }
    public Page<Transaction> findAllByEmitterAndReceiverId(int idUser, Pageable pageableParam) {
        return transactionRepository.findAllByUserEmitterAndUserReceiver_Id(idUser, pageableParam);
    }

  public   Page<Transaction> findAllByUserSender_idOrUserReceiver_id(final int senderId,final int receiverId, Pageable pageableParam){
       return transactionRepository.findAllByUserSender_idOrUserReceiver_id( senderId, receiverId, pageableParam) ;
  };
}
