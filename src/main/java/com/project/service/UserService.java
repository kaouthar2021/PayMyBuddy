package com.project.service;

import com.project.dto.UserRegistrationDto;
import com.project.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    User save(UserRegistrationDto registrationDto);

    public UserDetails loadUserByUsername(String username);
    List<User> getUsersFriends(String emailConnectedUser);
    Boolean existsByEmail(String email);
   User findUserByEmail(String email);
    User getCurrentUser(String emailConnectedUser);
    public void saveUser(User user);
    public void deleteUserFriendByEmail(String email);
}
