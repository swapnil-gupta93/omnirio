package com.omnirio.account.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCustomerDto {
	
	private AccountDto account;
	
	private CustomerDto customer;
	

}
