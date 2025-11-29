package com.obs.banking_app.service;

import org.springframework.http.ResponseEntity;

import com.obs.banking_app.dto.CustomerDto;

public interface CustomerService {
    ResponseEntity<CustomerDto> getCustomerById(Long id);
    ResponseEntity<CustomerDto> createCustomer(CustomerDto customerDto);
    ResponseEntity<CustomerDto> updateCustomer(Long id, CustomerDto customerDto);
    ResponseEntity<Void> deleteCustomer(Long id);
    
}
