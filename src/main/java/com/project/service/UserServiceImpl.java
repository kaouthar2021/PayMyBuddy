package com.project.service;

import com.project.dto.UserRegistrationDto;
import com.project.model.User;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService , UserDetailsService {

  @Autowired
  private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public User save(UserRegistrationDto registrationDto) {
        String pwd = encoder.encode(registrationDto.getPassword());
        User user = new User(registrationDto.getFirstName(),
                registrationDto.getLastName(), registrationDto.getEmail(),
                pwd);

        return userRepository.save(user);
    }
    @Override
    public  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

    public List<User> getUsersFriends(String emailConnectedUser) {
        User connectedUser = userRepository.findByEmail(emailConnectedUser);
        return connectedUser.getFriends();
    }
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    public void addFriend(String userEmail, String friendEmail) {
        User connectedUser = userRepository.findByEmail(userEmail);
        User friendUser = userRepository.findByEmail(friendEmail);

        Optional<User> isAlreadyFriend = connectedUser.getFriends().stream()
                .filter(friend -> friend.getEmail().equals(friendUser.getEmail())).findFirst();
        if (friendUser != connectedUser && friendEmail != userEmail) {
            if (isAlreadyFriend.isPresent()) {
                throw new RuntimeException("This user is already in this list");
            }

            List<User> friendsList = connectedUser.getFriends();
            friendsList.add(friendUser);
            userRepository.save(connectedUser);

        } else {
            throw new IllegalArgumentException("Your account not is user friend!");
        }
    }


}

