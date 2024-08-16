package com.example.ticketBooking.service;
import com.example.ticketBooking.DTO.LoginDTO;
import com.example.ticketBooking.model.User;
import com.example.ticketBooking.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    private UserRepo userRepository;
    private PasswordEncoder passwordEncoder;

    public String createUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            return "Email already exists";
        } else {
            String hashedPassword = passwordEncoder.encode(user.getPasswordHash());
            user.setPasswordHash(hashedPassword);
            userRepository.save(user);
            return "Customer Successfully Registered";
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
//
//    public User getUserById(Long id) {
//        User user = userRepository.findById(id).orElse(null);
//        if (user != null) {
//            logger.info("User found: {}", user);
//            return user;
//        } else {
//            logger.info("User with id {} not found", id);
//            return null;
//        }
//
//    }
//
//    public User updateUser(Integer id, User user) {
//        user.setUser_ID(id);
//        return userRepository.save(user);
//    }
//
//    public void deleteUser(Long id) {
//        userRepository.deleteById(id);
//    }

    // Logic for Login for any users in the system
    public ResponseEntity<Map<String, String>> userLogin(LoginDTO loginDTO) {

        String userRole = loginDTO.getRole();
        String userEmail = loginDTO.getEmail();
        String userPassword = loginDTO.getPassword();

        if (userRole != null) {
            if (userRole.equals("1")) {
                logger.info("User Role is Customer");
                return authenticateUser(userRepository, userEmail, userPassword, userRole);
            }

            return sendResponseMessage("Invalid User", null,null, HttpStatus.BAD_REQUEST);
        }

        return sendResponseMessage("User Role is null", null, null, HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<Map<String, String>> authenticateUser(UserRepo userRepository,
                                                                String userEmail,
                                                                String userPassword,
                                                                String userRole) {
        try {
            User relatedUser = userRepository.findByEmail(userEmail);
            //logger.info("Related User: {}", relatedUser);
            if (relatedUser != null) {
                String encodedPassword = relatedUser.getPasswordHash();
                logger.info("Encoded Password: {}", encodedPassword);
                boolean isPasswordCorrect = passwordEncoder.matches(userPassword, encodedPassword);
                logger.info(userPassword);
                logger.info("Is Password Correct: {}", isPasswordCorrect);
                if (isPasswordCorrect) {
                    String returnRole = String.valueOf(relatedUser.getRoleId());
                    String userId = String.valueOf(relatedUser.getUser_ID());
                    String successMessage = "Successfully Logged in";
                    logger.info("Return Role: {}", returnRole);
                    if (returnRole != null) {
                        return sendResponseMessage(successMessage, userId, returnRole, HttpStatus.OK);
                    }
                }
            }

            String errorMessage = "Authentication failed for " + userRole;
            return sendResponseMessage(errorMessage, null,null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();

            // Handle the exception and return an appropriate response
            String errorMessage = "An error occurred during authentication";
            return sendResponseMessage(errorMessage, null,null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    // Separate Method for create the Response
    public ResponseEntity <Map<String, String>> sendResponseMessage (String message, String userId, String returnRole, HttpStatus requestStatus) {
        Map <String, String> response = new HashMap<>();
        response.put("message", message);
        if (returnRole != null) {
            response.put("role", returnRole);
        }
        if (userId != null) {
            response.put("userId", userId);
        }
        response.put("status", String.valueOf(!requestStatus.isError()));
        return new ResponseEntity<>(response, requestStatus);
    }
}
