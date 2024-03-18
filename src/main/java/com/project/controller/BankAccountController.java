package com.project.controller;

import com.project.service.BankAccountService;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
//@RequestMapping("bankAccount")
public class BankAccountController {
@Autowired
    BankAccountService bankAccountService;
@Autowired
    UserService userService;


}
