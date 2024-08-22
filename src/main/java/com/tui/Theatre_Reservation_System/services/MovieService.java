package com.tui.Theatre_Reservation_System.services;

import com.tui.Theatre_Reservation_System.request.MovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    public String addMovie(MovieRequest movieRequest){

    }
}
