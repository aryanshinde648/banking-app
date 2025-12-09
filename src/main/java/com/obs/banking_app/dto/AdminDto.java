package com.obs.banking_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {

    private Long id;

    private String fName;

    private String lName;

    private String mName;

    private String fullName;

    private String username;

    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    private String role;

    @NotBlank(message = "Email cannot be blank")
    private String email;

    private String phoneNumber;
}
