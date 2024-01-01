package com.project.controller;

import com.project.dto.UserRegistrationDto;
import com.project.model.User;
import com.project.service.UserServiceImpl;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.LogManager.getLogger;

@Controller

public class UserController {
    private static final Logger logger = getLogger(UserController.class);

    @Autowired
    private UserServiceImpl userService;
    @ModelAttribute("/user")
    public UserRegistrationDto userRegistrationDto(){

        return new UserRegistrationDto();
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserRegistrationDto registrationDto) {

        userService.save(registrationDto);
        logger.info("user successfully registered");
        return "register";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserRegistrationDto user=new UserRegistrationDto();
        model.addAttribute("user",user);
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/contact")
    public ModelAndView showFriends(ModelAndView modelAndView, @NotNull Authentication auth) {
        modelAndView = new ModelAndView("contact");

        List<User> listFriends = userService.getUsersFriends(auth.getName());
        logger.info(listFriends);
        modelAndView.addObject("listFriends", listFriends);
        return modelAndView;

    }


    @GetMapping("/addContact")
    public String addContact(Model model,  Authentication auth) {

        Optional<List<User>> friends = Optional.ofNullable(userService.getUsersFriends(auth.getName()));
        model.addAttribute("user");
        return "addContact";
    }

    @PostMapping("/addContact")
    public String registerContactFriend(@ModelAttribute("User") UserRegistrationDto userRegistrationDto, Authentication auth) {
        if (auth != null && userService.existsByEmail(userRegistrationDto.getEmail())) {
            userService.addFriend(userRegistrationDto.getEmail(), auth.getName());
            logger.info("save contact");
            return "redirect:/addContact?success";
        } else {

            logger.error("Authentication object is null or email does not exist.");
            return "redirect:/addContact?error";
        }
    }



}

