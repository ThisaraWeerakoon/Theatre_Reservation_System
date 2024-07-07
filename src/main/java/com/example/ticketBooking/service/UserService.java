package com.example.ticketBooking.service;
import com.example.ticketBooking.model.User;
import com.example.ticketBooking.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

//    public User updateUser(Long id, User user) {
//        user.setId(id);
//        return userRepository.save(user);
//    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
