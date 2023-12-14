package com.project.PayMyBuddy.service;

import com.project.PayMyBuddy.model.User;
import com.project.PayMyBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public User createUser(User user){
         user.getFirstName();
        user.getLastName();
        user.getEmail();
        user.getPassword();
        user.getSolde();
        return userRepository.save(user);


    }
}
