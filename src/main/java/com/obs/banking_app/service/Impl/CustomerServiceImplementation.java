package com.obs.banking_app.service.Impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.obs.banking_app.dto.CustomerDto;
import com.obs.banking_app.entity.Customer;
import com.obs.banking_app.mapper.CustomerConversion;
import com.obs.banking_app.repository.CustomerRepository;
import com.obs.banking_app.service.CustomerService;

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
    public Map<String, Object> createCustomer(CustomerDto customerDto) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Validate unique username
            if (customerRepository.findByUsername(customerDto.getUsername()).isPresent()) {
                response.put("success", false);
                response.put("error", "Username already exists");
                return response;
            }

            // Validate unique email
            if (customerRepository.findByEmail(customerDto.getEmail()).isPresent()) {
                response.put("success", false);
                response.put("error", "Email already exists");
                return response;
            }

            // Create and save user (with plaintext password for now - not secure)
            Customer customer = new Customer();
            customer.setFName(customerDto.getFName());
            customer.setMName(customerDto.getMName());
            customer.setLName(customerDto.getLName());
            customer.setGender(customerDto.getGender());
            customer.setUsername(customerDto.getUsername());
            customer.setPassword(customerDto.getPassword());
            customer.setEmail(customerDto.getEmail());
            customer.setPhoneNumber(customerDto.getPhoneNumber());
            customer.setAddress(customerDto.getAddress());

            Customer savedCustomer = customerRepository.save(customer);

            // Return success response
            response.put("success", true);
            response.put("message", "Registration successful");
            response.put("customer", customerConversion.toCustomerDto(savedCustomer));
            log.info("New customer registered: {}", customerDto.getUsername());
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", "An error occurred during registration");
            log.error("Registration error: ", e);
        }

        return response;
    }

    @Override
    public Map<String, Object> updateCustomer(Long id, CustomerDto customerDto) {
        
        Map<String, Object> response = new HashMap<>();

        try {

            log.info("Updating Customer Profile for customer ID: {}", id);

            Customer customer = customerRepository.findById(id).orElseThrow(() -> {
                log.error("Customer Not Found with Id = ",id);
                return new RuntimeException("Customer Not Found with Id = " + id);
            });

            if (customerDto.getUsername() != null && !customerDto.getUsername().isEmpty()) {
                // Check if username is already taken by another user
                Optional<Customer> existingCustomer = customerRepository.findByUsername(customerDto.getUsername());
                if (existingCustomer.isPresent() && !existingCustomer.get().getId().equals(id)) {
                    response.put("success", false);
                    response.put("error", "Username already taken");
                    return response;
                }
                customer.setUsername(customerDto.getUsername());
            }

            if (customerDto.getEmail() != null && !customerDto.getEmail().isEmpty()) {
                // Check if email is already taken by another user
                Optional<Customer> existingCustomer = customerRepository.findByEmail(customerDto.getEmail());
                if (existingCustomer.isPresent() && !existingCustomer.get().getId().equals(id)) {
                    response.put("success", false);
                    response.put("error", "Email already taken");
                    return response;
                }
                customer.setEmail(customerDto.getEmail());
            }

            if (customerDto.getPassword() != null && !customerDto.getPassword().isEmpty()) {
                // Encrypt password
                customer.setPassword(customerDto.getPassword());
            }

            Customer updatedCustomer = customerRepository.save(customer);
            response.put("success", true);
            response.put("message", "Profile updated successfully");
            response.put("customer", customerConversion.toCustomerDto(updatedCustomer));

        } catch (Exception e) {
            response.put("success", false);
            response.put("error", "Failed to update profile: " + e.getMessage());
            log.error("Update error: ", e);
        }

        return response;
    }

    @Override
    public Map<String, Object> deleteCustomer(Long id) {
        
        Map<String, Object> response = new HashMap<>();

        try {
            log.info("Deleting Customer with ID: {}", id);

            Customer customer = customerRepository.findById(id).orElseThrow(() -> {
                log.error("Customer Not Found with Id = ",id);
                return new RuntimeException("Customer Not Found with Id = " + id);
            });

            customerRepository.delete(customer);

            response.put("success", true);
            response.put("message", "Customer deleted successfully");


        } catch (Exception e) {
            response.put("success", false);
            response.put("error", "Failed to delete customer: " + e.getMessage());
            log.error("Deletion error: ", e);
        }

        return response;
    }

    
    
}
