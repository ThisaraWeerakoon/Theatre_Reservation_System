package com.example.ticketBooking.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Movie")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Movie_ID")
    private Integer Movie_ID;

    @Column(name = "Movie_Title",length = 100)
    private String Movie_Title;

    @Column(name = "Movie_Description", length = 255)
    private String Movie_Description;

    @Column(name = "Genre",length=50)
    private String Genre;

    @Column(name = "Language",length=50)
    private String Language;


}
