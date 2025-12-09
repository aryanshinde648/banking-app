package com.obs.banking_app.dto;

import java.time.LocalDateTime;

import com.obs.banking_app.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Customer customer;

    private Long id;
    
    private Long accountNumber;
    
    private String accountType;
    
    private Double balance;

    private Long customerId;

    private String status;

    private LocalDateTime accountOpenedAt;
}
