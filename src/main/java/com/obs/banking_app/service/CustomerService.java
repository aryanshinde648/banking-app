package com.obs.banking_app.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.obs.banking_app.dto.CustomerDto;

public interface CustomerService {
    
    ResponseEntity<CustomerDto> getCustomerById(Long id);
    Map<String, Object> createCustomer(CustomerDto customerDto);
    Map<String, Object> updateCustomer(Long id, CustomerDto customerDto);
    Map<String, Object> deleteCustomer(Long id);
    
}
