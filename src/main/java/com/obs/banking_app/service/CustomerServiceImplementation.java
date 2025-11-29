package com.obs.banking_app.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.obs.banking_app.dto.CustomerDto;
import com.obs.banking_app.entity.Customer;
import com.obs.banking_app.mapper.CustomerConversion;
import com.obs.banking_app.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImplementation implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerConversion customerConversion;

    public CustomerServiceImplementation(CustomerRepository customerRepository,
            CustomerConversion customerConversion) {
        this.customerRepository = customerRepository;
        this.customerConversion = customerConversion;
    }

    @Override
    public ResponseEntity<CustomerDto> getCustomerById(Long id) {
        log.info("Getting Customer By Id = ",id);
        Customer customer = customerRepository.findById(id).orElseThrow(() -> {
            log.error("Customer Not Found with Id = ",id);
            return new RuntimeException("Customer Not Found with Id = " + id);
        });
        return ResponseEntity.ok(customerConversion.toCustomerDto(customer));
        
    }

    @Override
    public ResponseEntity<CustomerDto> createCustomer(CustomerDto customerDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createCustomer'");
    }

    @Override
    public ResponseEntity<CustomerDto> updateCustomer(Long id, CustomerDto customerDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCustomer'");
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCustomer'");
    }
    
}
