package com.project.controller;

import com.project.service.TransactionServiceImpl;
import com.project.service.UserService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import static org.apache.logging.log4j.LogManager.getLogger;

@Controller
public class TransactionController {
    private final TransactionServiceImpl transactionService;
    private final UserService userService;
    private static final Logger logger = getLogger(TransactionController.class);

    @Autowired
    public TransactionController(TransactionServiceImpl transactionService,UserService userService) {
        this.transactionService = transactionService;
        this.userService=userService;
    }

//    @PostMapping("/addTransaction")
//    public String saveTransaction(@ModelAttribute("transaction") TransactionDto transactionDto, Authentication auth) {
//        ModelAndView modelAndView = new ModelAndView("redirect:/transfer");
//        modelAndView.addObject("postTransaction", transactionDto);
//
//        Optional<User> user = Optional.ofNullable(userService.findUserByEmail(auth.getName()));
//
//        transactionService.addTransaction(user.get().getId(), transactionDto.getConnection(), transactionDto.getDescription(), transactionDto.getAmount());
//
//        return modelAndView;
//    }

}
