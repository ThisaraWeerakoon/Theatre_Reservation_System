package com.example.ticketBooking.repository;
import com.example.ticketBooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    User findByEmail(String email);
}