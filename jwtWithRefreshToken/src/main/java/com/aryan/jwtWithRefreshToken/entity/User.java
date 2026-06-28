package com.aryan.jwtWithRefreshToken.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "applicationUser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Full name cannot be empty")
    @Size(min = 3, max = 30, message = "Full Name length should be 3-30 characters")
    @Column(nullable = false)
    private String fullName;

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, max = 30, message = "Full Name length should be 3-30 characters")
    @Column(nullable = false)
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 3, max = 30, message = "Full Name length should be 3-30 characters")
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
