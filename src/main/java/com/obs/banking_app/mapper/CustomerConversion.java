package com.obs.banking_app.mapper;

import com.obs.banking_app.dto.CustomerDto;
import com.obs.banking_app.entity.Customer;

public interface CustomerConversion {

    Customer toCustomer(CustomerDto customerDto);
    
    CustomerDto toCustomerDto(Customer customer);
}
