package com.omnirio.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omnirio.customer.constants.ApiConstants;
import com.omnirio.customer.constants.MessageConstants;
import com.omnirio.customer.dto.CustomerDto;
import com.omnirio.customer.dto.ResponseDto;
import com.omnirio.customer.service.CustomerService;

@RestController
@RequestMapping(ApiConstants.CUSTOMER)
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
//	@Autowired
//	AuthenticationManager authenticationManager;
//	
//	@Autowired
//	TokenProvider jwtTokenUtil;
	
	@PostMapping(value = "/register")
	public ResponseDto registerUser(@RequestBody CustomerDto customerDto) {
		CustomerDto createdCustomer = customerService.save(customerDto); 
		if(null != createdCustomer) {
			return new ResponseDto(true, createdCustomer, MessageConstants.RECORDS_CREATED_SUCCESSFULLY);
		}
		return new ResponseDto(false, null, MessageConstants.RECORDS_CREATED_SUCCESSFULLY);
	}
	
//	@PostMapping(value = "/login")
//	public ResponseEntity<?> generateToken(@RequestBody LoginDto loginDto) {
//		final Authentication authentication = authenticationManager.authenticate(
//			new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
//		
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//        final String token = jwtTokenUtil.generateToken(authentication);
//        return ResponseEntity.ok("test");
//	}
	
	//@PreAuthorize("hasRole('USER')")
	@GetMapping("/{userName}")
	public ResponseDto fetchCustomerDetails(@PathVariable String userName) {
		return new ResponseDto(true,customerService.findByUsername(userName),MessageConstants.RECORDS_RETRIEVED_SUCCESSFULLY);
	}
	
	//@PreAuthorize("hasRole('BRANCH_MANAGER')")
	@PutMapping
	public ResponseDto updateCustomerDetails(@RequestBody CustomerDto customerDto) {
		return new ResponseDto(false, null, MessageConstants.RECORDS_CREATED_SUCCESSFULLY);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseDto fetchCustomerDetailsById(@PathVariable String userId) {
		CustomerDto customerDto = customerService.findByUserId(userId);
		if(null != customerDto) {
			return new ResponseDto(true, customerDto, MessageConstants.RECORDS_RETRIEVED_SUCCESSFULLY);
		}
		return new ResponseDto(false, null, MessageConstants.RECORDS_RETRIEVED_SUCCESSFULLY);
	}
	
	@GetMapping("/all")
	public ResponseDto fetchAllCustomers() {
		List<CustomerDto> customerDtoList = customerService.findAll();
		if(!customerDtoList.isEmpty()) {
			return new ResponseDto(true, customerDtoList, MessageConstants.RECORDS_RETRIEVED_SUCCESSFULLY);
		}
		return new ResponseDto(false, null, MessageConstants.RECORDS_RETRIEVED_SUCCESSFULLY);
	}
	

}
