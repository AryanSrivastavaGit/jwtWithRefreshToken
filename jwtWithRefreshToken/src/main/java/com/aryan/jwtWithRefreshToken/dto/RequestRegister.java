package com.aryan.jwtWithRefreshToken.dto;

import com.aryan.jwtWithRefreshToken.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RequestRegister {

    @NotBlank(message = "Full name cannot be empty")
    @Size(min = 3, max = 30, message = "Full Name length should be 3-30 characters")
    private String fullName;

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, max = 30, message = "Full Name length should be 3-30 characters")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 3, max = 30, message = "Full Name length should be 3-30 characters")
    private String password;

    private Role role;
}
