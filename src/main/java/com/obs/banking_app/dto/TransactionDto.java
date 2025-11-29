package com.obs.banking_app.dto;

import java.time.LocalDateTime;

import com.obs.banking_app.enumDto.TransactionStatus;
import com.obs.banking_app.enumDto.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private Long id;

    private Long transactionId;
    
    private TransactionType transactionType;
    
    private Double amount;

    private Long fromAccountId;

    private Long toAccountId;
  
    private LocalDateTime timestamp;
 
    private TransactionStatus status;
}
