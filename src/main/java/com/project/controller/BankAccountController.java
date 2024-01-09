package com.project.controller;

import com.project.model.BankAccount;
import com.project.model.User;
import com.project.service.BankAccountService;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BankAccountController {
@Autowired
    BankAccountService bankAccountService;
@Autowired
    UserService userService;

    @PostMapping("/addBankAccount")
    public String addBankAccount(@ModelAttribute("bankAccount") final BankAccount bankAccount, Authentication auth) {

        User user = userService.findUserByEmail(auth.getName());
        bankAccountService.addBankAccount(user,bankAccount);
        //ModelAndView modelAndView = new ModelAndView("redirect:/contact");
       // modelAndView.addObject("addBankAccount", bankAccount);
      //  return modelAndView;
      //  return new ModelAndView("redirect:/addContact");
        return "redirect:/addBankAccount?success";
    }

}
