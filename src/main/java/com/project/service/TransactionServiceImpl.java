package com.project.service;

import com.project.repository.TransactionRepository;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    private  final TransactionRepository transactionRepository;

    private final  UserService userService;

    private final   UserRepository userRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository ,UserService userService,UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userService=userService;
        this.userRepository=userRepository;
    }


//    public Transaction addTransaction(TransactionDto transactionDto){
//        Double amount=transactionDto.getAmount();
//        String connection=transactionDto.getConnection();
//       double commission = amount * 0.005 / 100;
//       double amountWithCommission = amount + 0.005 * 100 / amount;
//        Authentication auth = null;
//       User userSender=userService.getCurrentUser(auth.getName());
//       User userReceiver=userService.findUserByEmail(connection);
//       if (userSender.getSolde() < amountWithCommission) {
//           throw new RuntimeException("Not enough money on your account");
//       }
//       Transaction transaction=new Transaction();
//       transaction.setAmount(amount);
//       transaction.setTransaction_date(new Date());
//       transaction.setUserSender(userSender);
//       transaction.setUserReceiver(userReceiver);
//       transaction.setDescription(description);
//
//       transactionRepository.save(transaction);
//       TransactionDto transactionDto = new TransactionDto();
//       transactionDto.setConnection(userReceiver.getUsername());
//       transactionDto.setDescription(description);
//       transactionDto.setAmount(amount);
//
//
//       userSender.setSolde(userSender.getSolde() - amountWithCommission);
//       userReceiver.setSolde(userReceiver.getSolde() + amount);
//
//       userService.saveUser(userSender);
//       userService.saveUser(userReceiver);
//       return transaction;
//   }

}
