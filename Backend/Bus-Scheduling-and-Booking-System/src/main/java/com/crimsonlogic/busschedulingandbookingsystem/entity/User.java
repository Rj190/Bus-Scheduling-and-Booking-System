package com.crimsonlogic.busschedulingandbookingsystem.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_info")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userID;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private String userRole; // Role can be "User" or "Admin"

}
