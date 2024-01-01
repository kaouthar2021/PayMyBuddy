package com.project.service;

import com.project.dto.UserRegistrationDto;
import com.project.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    User save(UserRegistrationDto registrationDto);

    public UserDetails loadUserByUsername(String username);
}
