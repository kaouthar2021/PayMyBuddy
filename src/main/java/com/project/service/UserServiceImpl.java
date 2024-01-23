package com.project.service;

import com.project.dto.UserRegistrationDto;
import com.project.model.User;
import com.project.repository.UserRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;


@Service
public class UserServiceImpl implements UserService , UserDetailsService {

  @Autowired
  private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    private static final Logger logger = getLogger(UserServiceImpl.class);
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

   public User findUserByEmail(String email){return userRepository.findByEmail(email);}
    public User getCurrentUser(String emailConnectedUser) {
        User connectedUser = userRepository.findByEmail(emailConnectedUser);
        return connectedUser;
    }
    public void addFriend(String userEmail, String friendEmail) {
        User connectedUser = userRepository.findByEmail(userEmail);
        User friendUser = userRepository.findByEmail(friendEmail);
        List<User> userFriends=connectedUser.getFriends();
        for ( User userFriend:userFriends){
            logger.info("name:"+userFriend.getEmail());
        }
        logger.info("friendName:"+friendUser.getEmail());
        if (friendUser != null && connectedUser != null && !friendEmail.equals(userEmail)) {
            if (!connectedUser.getFriends().contains(friendUser)) {
                connectedUser.getFriends().add(friendUser);
                userRepository.save(connectedUser);
            } else {
                throw new RuntimeException("This user is already in the friend list");
            }
        } else {
            throw new IllegalArgumentException("Invalid user or friend email");
        }
    }

    public void saveUser(User user) {

        userRepository.save(user);
    }

}

