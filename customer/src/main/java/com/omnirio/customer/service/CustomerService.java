package com.omnirio.customer.service;

import java.util.List;

import com.omnirio.customer.dto.CustomerDto;

public interface CustomerService {
	
	CustomerDto save(CustomerDto customer);
	
	CustomerDto findByUsername(String userName);
	
	CustomerDto updateCustomer(CustomerDto customerDto);
	
	CustomerDto findByUserId(String userId);
	
	List<CustomerDto> findAll();

}
