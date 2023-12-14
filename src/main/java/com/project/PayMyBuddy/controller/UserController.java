package com.project.PayMyBuddy.controller;

import com.project.PayMyBuddy.model.User;
import com.project.PayMyBuddy.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class UserController {
    private static final Logger logger = LogManager.getLogger("UserController");

    @Autowired
    UserService userService;
    @PostMapping("/create")
    public String createUser(@RequestBody User user) {
        userService.createUser(user);
        logger.info("user authenticated");
        return "redirect:/home";
    }
}
