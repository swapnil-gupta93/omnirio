package com.omnirio.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omnirio.account.constants.ApiConstants;
import com.omnirio.account.constants.MessageConstants;
import com.omnirio.account.dto.AccountCustomerDto;
import com.omnirio.account.dto.AccountDto;
import com.omnirio.account.dto.ResponseDto;
import com.omnirio.account.service.AccountService;

@RestController
@RequestMapping(ApiConstants.ACCOUNT)
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@PostMapping
	public ResponseDto createAccount(@RequestBody AccountDto accountDto) {
		AccountDto createdAccountDto = accountService.createAccount(accountDto);
		if(null != createdAccountDto) {
			return new ResponseDto(true, createdAccountDto, MessageConstants.RECORDS_CREATED_SUCCESSFULLY);
		}
		return new ResponseDto(false, null, MessageConstants.RECORDS_CREATED_SUCCESSFULLY);
	}
	
	@GetMapping("/{accountId}")
	public ResponseDto getAccount(@PathVariable String accountId) {
		AccountDto accountDto = accountService.getAccountById(accountId);
		if(null != accountDto) {
			return new ResponseDto(true, accountDto, MessageConstants.RECORDS_RETRIEVED_SUCCESSFULLY);
		}
		
		return new ResponseDto(false, null, MessageConstants.RECORDS_CREATED_SUCCESSFULLY);
	}
	
	@DeleteMapping("/{accountId}")
	public ResponseDto deleteAccount(@PathVariable String accountId) {
		boolean isDeleted = accountService.deleteAccount(accountId);
		if(isDeleted) {
			return new ResponseDto(true, null, MessageConstants.RECORDS_DELETED_SUCCESSFULLY);
		}
		
		return new ResponseDto(false, null, MessageConstants.RECORDS_CREATED_SUCCESSFULLY);
	}
	
	@PostMapping(value = "/customer")
	public ResponseDto createAccountCustomer(@RequestBody AccountCustomerDto accountCustomerDto ) {
		AccountDto accountDto = accountService.createCustomerAccount(accountCustomerDto);
		if(null != accountDto) {
			return new ResponseDto(true, accountDto, MessageConstants.RECORDS_CREATED_SUCCESSFULLY);
		}
		return new ResponseDto(false, null, MessageConstants.RECORDS_CREATED_SUCCESSFULLY);
	}
	
	@GetMapping(value = "/all")
	public ResponseDto fetchAllAccounts() {
		List<AccountDto> accountDtoList = accountService.findAll();
		if(!accountDtoList.isEmpty()) {
			return new ResponseDto(true, accountDtoList, MessageConstants.RECORDS_CREATED_SUCCESSFULLY);
		}
		
		return new ResponseDto(false, null, MessageConstants.RECORDS_CREATED_SUCCESSFULLY);
	}

}
