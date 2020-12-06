package com.omnirio.account.service;

import java.util.List;

import com.omnirio.account.dto.AccountCustomerDto;
import com.omnirio.account.dto.AccountDto;

public interface AccountService {
	
	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(String accountId);
	
	boolean deleteAccount(String accountId);
	
	AccountDto createCustomerAccount(AccountCustomerDto accountCustomerDto);
	
	List<AccountDto> findAll();

}
