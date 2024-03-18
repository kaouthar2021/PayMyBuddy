package com.project.controller;

import com.project.dto.TransactionDto;
import com.project.model.Transaction;
import com.project.model.User;
import com.project.service.TransactionServiceImpl;
import com.project.service.UserService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

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
   @GetMapping("/transfer")
   public String showTransferForm(Model model,Authentication auth,@RequestParam(name = "page", defaultValue = "1") int page,
                                  @RequestParam(name = "size", defaultValue = "5") int size) {

       User user = userService.findUserByEmail(auth.getName());
       TransactionDto transactionDto=new TransactionDto();
       model.addAttribute("postTransaction",transactionDto);
       model.addAttribute("friendList",userService.getUsersFriends(user.getEmail()));
       Page<Transaction> transactionsPage= transactionService.findAllByUserSender_idOrUserReceiver_id(user.getId(), user.getId(),PageRequest.of(page - 1, size, Sort.by("Id").descending()));

       model.addAttribute("transactions",  transactionsPage);
       model.addAttribute("totalPages",transactionsPage.getTotalPages());
       model.addAttribute("currentPage",page);
       return "transfer";

   }


    @PostMapping("/transfer")
    public ModelAndView saveTransaction(@ModelAttribute("transaction") TransactionDto transactionDto, Authentication auth) {
        Optional<User> user = Optional.ofNullable(userService.findUserByEmail(auth.getName()));

        ModelAndView modelAndView = new ModelAndView("redirect:/transfer");
        modelAndView.addObject("friendList",userService.getUsersFriends(user.get().getEmail()));
        modelAndView.addObject("postTransaction", transactionDto);


        transactionService.addTransaction(transactionDto,auth);

        return modelAndView;
    }

}
