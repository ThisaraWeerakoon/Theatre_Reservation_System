package com.example.ticketBooking.repository;
import com.example.ticketBooking.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Long> {
}