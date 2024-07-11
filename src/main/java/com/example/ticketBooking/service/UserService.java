package com.example.ticketBooking.service;
import com.example.ticketBooking.model.User;
import com.example.ticketBooking.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepo userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public String getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            logger.info("User found: {}", user);
            return user.getName();
        } else {
            logger.info("User with id {} not found", id);
            return "Id not found";
        }

    }
//    public User updateUser(Long id, User user) {
//        user.setId(id);
//        return userRepository.save(user);
//    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
