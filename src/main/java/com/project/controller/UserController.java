package com.project.controller;

import com.project.dto.FriendDto;
import com.project.dto.UserRegistrationDto;
import com.project.model.User;
import com.project.repository.UserRepository;
import com.project.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

@Controller

public class UserController {
    private static final Logger logger = getLogger(UserController.class);

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserRepository userRepository;
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

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login?logout";
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

            return "redirect:/login";
        }
    }


@PostMapping("/addContact")
public String registerContactFriend(FriendDto friendDto, Authentication auth) {
    if (auth != null && userService.existsByEmail(friendDto.getEmail())) {
        userService.addFriend( auth.getName(),friendDto.getEmail());
        logger.info("Friend added successfully");
        return "redirect:/addContact?success";
    } else {
        logger.error("Authentication object is null or email does not exist.");
        return "redirect:/addContact?error";
    }
}

    @GetMapping("/contact/{email}")
    public String deleteFriend(@PathVariable String email,@NotNull Authentication auth) throws RuntimeException{

        List<User> listFriends = userService.getUsersFriends(auth.getName());

     ;
        User currentUser = userService.getCurrentUser(auth.getName());
        User contactToDelete = null;

        if (currentUser != null) {
            for (User friend : currentUser.getFriends()) {
                if (friend.getEmail().equals( email)) {
                    contactToDelete = friend;
                    break; //
                }
            }
        }

        if (contactToDelete == null) {
            throw new RuntimeException("email Not Found");
        }

        listFriends.remove(contactToDelete);
        userRepository.save(userService.getCurrentUser(auth.getName()));

        return "redirect:/contact";
    }







    }



