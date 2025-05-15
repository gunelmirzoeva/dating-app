package com.datingapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true, nullable = false)
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;


    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password; // Hashed password


    @Column(unique = true,  nullable = false)
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Column
    private LocalDateTime deletedAt;
    @Column
    private String name;


    @Column
    @Min(value = 18, message = "Age must be at least 18")
    private int age;

    public enum Gender {
        MALE, FEMALE, NON_BINARY
    }

    @Enumerated(EnumType.STRING)
    @Column
    private Gender gender;

    @Column(length = 500)
    @Size(max = 500, message = "Bio must be 500 characters or less")
    private String bio;

    @Column
    @Size(max = 255, message = "Profile picture URL must be 255 characters or less")
    private String profilePicture;

    @Column
    @Size(max = 100, message = "Location must be 100 characters or less")
    private String location;

    @Column(nullable = false)
    private boolean isActive = true;

    @Column
    private LocalDateTime lastLogin;

    @Column
    private String provider;

    @Column
    private String providerId;

    public enum Role {
        USER, ADMIN
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.USER;

    @Column(columnDefinition = "jsonb")
    @NotBlank(message = "Preferences cannot be empty")
    private String preferences;


    @Column(nullable = false)
    private boolean isEmailVerified = false;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
