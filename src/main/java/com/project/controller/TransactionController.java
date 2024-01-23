package com.project.controller;

import com.project.dto.TransactionDto;
import com.project.model.Transaction;
import com.project.model.User;
import com.project.service.TransactionServiceImpl;
import com.project.service.UserService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
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
   public String showTransferForm(Model model,Authentication auth, @RequestParam(name = "pageNo" , defaultValue = "1")int pageNo) {
       int pageSize = 5;
       User user = userService.findUserByEmail(auth.getName());
       TransactionDto transactionDto=new TransactionDto();
       model.addAttribute("postTransaction",transactionDto);
       model.addAttribute("friendList",userService.getUsersFriends(user.getEmail()));
       Page<Transaction> page = transactionService.findPaginated(pageNo, pageSize);
       List<Transaction> listTransactions = page.getContent();



//       Sort sort = Sort.by(Sort.Order.desc("date"));
//       Pageable pageable= PageRequest.of(0,10);//nbre de transaction par page
//      Page<Transaction> transactionPage = transactionService.getAllTransactionsSorted(user.getId(), pageable);

       //model.addAttribute("transactions", page.getContent());
       model.addAttribute("transactions", listTransactions);
       model.addAttribute("currentPage", page.getNumber());
       model.addAttribute("totalPages", page.getTotalPages());
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
