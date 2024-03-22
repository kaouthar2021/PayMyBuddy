package com.project.controller;


import com.project.model.BankAccount;
import com.project.model.User;
import com.project.service.BankAccountService;
import com.project.service.UserService;
import com.project.utils.Constante;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller

public class BankAccountController {
@Autowired
    BankAccountService bankAccountService;
@Autowired
    UserService userService;
    @GetMapping("/profile")
    public ModelAndView showUserProfile(Model model, @NotNull  Authentication auth) {
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("user", user);
        model.addAttribute("listBankAccount", List.of(user.getBankAccount()));
        return new ModelAndView("profile");
    }
    @PostMapping("/addBankAccount")
    public ModelAndView addBankAccount(@ModelAttribute("bankAccount") final BankAccount bankAccount, Authentication auth) {

        User user = new User();
        user = userService.findUserByEmail(auth.getName());
        ModelAndView modelAndView = new ModelAndView("redirect:/profile");
        bankAccountService.addBankAccount(user, bankAccount);
        modelAndView.addObject("addBankAccount", bankAccount);
        return modelAndView;
    }
    @PostMapping("/transferToMyBankAccount")
    public ModelAndView transferToMyBankAccount(@RequestParam(value = "id") String iban, @RequestParam(value = "amount") double amount, Authentication auth) {

        User user = new User();
        user = userService.findUserByEmail(auth.getName());

        ModelAndView modelAndView = new ModelAndView("redirect:/profile");
        double amountMax = user.getSolde() * (1 - Constante.COMMISSION);
        bankAccountService.transferToOrFromMyBankAccount(user, iban, amount * -1.0);
        modelAndView.addObject("amountMax", amountMax);
        return modelAndView;
    }

    @PostMapping("/transferFromMyBankAccount")
    public ModelAndView transferFromMyBankAccount(@RequestParam(value = "id") String strIBANAccount, @RequestParam(value = "amount") double amount, Authentication auth) {

        User user = new User();
        user = userService.findUserByEmail(auth.getName());

        ModelAndView modelAndView = new ModelAndView("redirect:/profile");
        bankAccountService.transferToOrFromMyBankAccount(user, strIBANAccount, amount);
        return modelAndView;
    }

}
