package com.example.ticketBooking.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_ID")
    private Integer User_ID;

    @Column(name = "User_Name",length = 50)
    private String name;

    @Column(name = "User_email_address", length = 50)
    private String email;

    @Column(name = "User_contact_number",length=20)
    private String contactNumber;

    @Column(name = "Role_ID")
    private Integer roleId;

    @Column(name = "PasswordHash", length=100)
    private String passwordHash;

}
