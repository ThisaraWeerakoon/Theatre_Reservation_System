package com.example.ticketBooking.model;

import jakarta.persistence.*;


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_ID", length = 10)
    private String User_ID;

    @Column(name = "User_Name",length = 20)
    private String name;

    @Column(name = "User_email_address", length = 20)
    private String email;

    @Column(name = "User_contact_number",length=15)
    private String contactNumber;

    @Column(name = "Role_ID", length = 20)
    private String roleId;

    @Column(name = "PasswordHash", length=100)
    private String passwordHash;

}
