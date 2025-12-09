package com.obs.banking_app.mapper;

import org.mapstruct.Mapper;

import com.obs.banking_app.dto.CustomerDto;
import com.obs.banking_app.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerConversion {

    Customer toCustomer(CustomerDto customerDto);
    
    CustomerDto toCustomerDto(Customer customer);
}
