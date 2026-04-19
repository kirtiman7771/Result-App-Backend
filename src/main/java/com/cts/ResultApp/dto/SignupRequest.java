package com.cts.ResultApp.dto;

import com.cts.ResultApp.model.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email(message = "Email must be valid")
    private String emailId;

    @NotNull
    private UserRole role; // Must be ROLE_STUDENT or ROLE_TEACHER
}

//SignupRequest -> ResultService -> AuthController
