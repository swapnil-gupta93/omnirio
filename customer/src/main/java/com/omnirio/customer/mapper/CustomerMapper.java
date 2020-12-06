package com.omnirio.customer.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.omnirio.customer.dto.CustomerDto;
import com.omnirio.customer.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

	CustomerDto customerToCustomerDto(Customer customer);
	
	Customer customerDtoToCustomer(CustomerDto customer);
	
	List<CustomerDto> customerListToCustomerDtoList(List<Customer> customer);
}
