package com.obs.banking_app.dto;

import java.util.List;

import com.obs.banking_app.entity.Account;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private Long id;
    
    private String fName;

    private String mName;

    private String lName;

    private String fullName = fName+" "+mName+" "+lName;

    private String email;

    private String phoneNumber;

    private String address;

    private String password;

    private String dob;

    private String gender;

    private String username;

    private List<Account> accounts;
}
