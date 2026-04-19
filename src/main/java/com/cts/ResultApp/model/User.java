package com.cts.ResultApp.model;

import com.cts.ResultApp.model.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password; // Hashed password

    @Column(unique = true, nullable = false) // Email is usually unique
    @Email
    private String emailId; // <-- NEW FIELD

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false) // Assuming you want lastName required (non-nullable)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;
}
