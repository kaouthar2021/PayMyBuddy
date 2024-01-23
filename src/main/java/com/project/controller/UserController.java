package com.project.controller;

import com.project.dto.FriendDto;
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
        if(userService.existsByEmail(registrationDto.getEmail())){
            logger.error("user already exist");
            return "redirect:/register?error=emailExists";
        }
        userService.save(registrationDto);
        logger.info("user successfully registered");
        return "redirect:/register?success";
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

    @GetMapping("/")
    public String home() {
        logger.info("get index page");
        return "index";
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
    public String addContact(Model model, Authentication auth) {
        if (auth != null) {
            List<User> friends = userService.getUsersFriends(auth.getName());
            model.addAttribute("friends", friends);
            return "addContact";
        } else {
            // Gérer le cas où l'authentification est null
            return "redirect:/login";
        }
    }


@PostMapping("/addContact")
public String registerContactFriend(FriendDto friendDto, Authentication auth) {
    if (auth != null && userService.existsByEmail(friendDto.getEmail())) {
        userService.addFriend(friendDto.getEmail(), auth.getName());
        logger.info("Friend added successfully");
        return "redirect:/addContact?success";
    } else {
        logger.error("Authentication object is null or email does not exist.");
        return "redirect:/addContact?error";
    }
}
    @GetMapping("/profile")
    public String showProfile(Model model, Authentication auth) {

        User user = userService.findUserByEmail(auth.getName());

        return "profile";


        }

    }



