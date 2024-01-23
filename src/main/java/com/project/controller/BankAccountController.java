package com.project.controller;

import com.project.dto.BankAccountDto;
import com.project.model.BankAccount;
import com.project.model.User;
import com.project.service.BankAccountService;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
//@RequestMapping("bankAccount")
public class BankAccountController {
@Autowired
    BankAccountService bankAccountService;
@Autowired
    UserService userService;
    @GetMapping("/bankAccount")
    public String showBankAccountForm(Model model, Authentication auth) {
        Optional<User> user = Optional.ofNullable(userService.findUserByEmail(auth.getName()));
        BankAccountDto bankAccountDto=new BankAccountDto();
        model.addAttribute("bankAccount", bankAccountDto);
        List<BankAccount> listBankAccount = bankAccountService.findAllByUserId(user.get().getId());
        model.addAttribute("listBankAccount", listBankAccount);
        return "bankAccount";
    }


    @PostMapping("/addBankAccount")
    public ModelAndView addBankAccount(@ModelAttribute("bankAccount") final BankAccountDto bankAccountDto, Authentication auth) {

        User user = userService.findUserByEmail(auth.getName());
        bankAccountService.addBankAccount(user,bankAccountDto);
        return new ModelAndView("redirect:/bankAccount").addObject("success", "Bank account added successfully.");
    }

    @GetMapping("/listBankAccount")
    public String showListBankAccount(Model model, Authentication auth) {
        User user = userService.findUserByEmail(auth.getName());
        List<BankAccount> listBankAccount = bankAccountService.findAllByUserId(user.getId());
        model.addAttribute("listBankAccount", listBankAccount);
        return "bankAccount";
    }
    @GetMapping("/sendMoney")
    public String showSendMoneyForm(Model model, Authentication auth) {
        User user = userService.findUserByEmail(auth.getName());
        List<BankAccount> listBankAccount = bankAccountService.findAllByUserId(user.getId());
        model.addAttribute("listBankAccount", listBankAccount);
        return "bankAccount";
    }


    @PostMapping("/sendMoney")
    public ModelAndView sendMoney( @RequestParam String iban, @RequestParam double amount, Authentication auth) {

        User user = userService.findUserByEmail(auth.getName());
        bankAccountService.sendMoney(user, iban, amount);
        return new ModelAndView("redirect:/bankAccount").addObject("success", "Money sent successfully.");

    }
    @GetMapping("/receiveMoney")
    public String showReceiveMoneyForm(Model model, Authentication auth) {
        User user = userService.findUserByEmail(auth.getName());
        List<BankAccount> listBankAccount = bankAccountService.findAllByUserId(user.getId());
        model.addAttribute("listBankAccount", listBankAccount);
        return "bankAccount";
    }
    @PostMapping("/receiveMoney")
    public ModelAndView receiveMoney(@RequestParam String iban, @RequestParam double amount, Authentication auth) {

        User user = userService.findUserByEmail(auth.getName());
        bankAccountService.receiveMoney(user, iban, amount);
        return new ModelAndView("redirect:/bankAccount").addObject("success", "Money received successfully.");

    }

}
