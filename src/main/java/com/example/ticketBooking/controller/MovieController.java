package com.example.ticketBooking.controller;
import com.example.ticketBooking.DTO.LoginDTO;
import com.example.ticketBooking.model.Movie;
import com.example.ticketBooking.service.MovieService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class MovieController {

    private static final Logger log = LoggerFactory.getLogger(MovieController.class);
    @Autowired
    private MovieService movieService;

    //    @PostMapping
//    public User createUser(@RequestBody User user) {
//        return userService.createUser(user);
//    }
//
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> createUser(@Valid @RequestBody User user) {
        String registrationResult = userService.createUser(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", registrationResult);

        if (registrationResult.equals("Email already exists")) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginHandle(@RequestBody LoginDTO loginDTO) {
        log.info("Login request received for user: {}", loginDTO.getEmail());
        return userService.userLogin(loginDTO);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
//
//    @GetMapping("/{id}")
//    public User getUserById(@PathVariable Long id) {
//        return userService.getUserById(id);
//    }
//
//    @PutMapping("/{id}")
//    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
//        return userService.updateUser(id, user);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//    }
}