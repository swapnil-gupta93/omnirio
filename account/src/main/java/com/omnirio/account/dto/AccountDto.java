package com.omnirio.account.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

	private String accountId;
	
	private String accountType;
	
	private LocalDateTime openDate;
	
	private String customerId;

	private String customerName;
	
	private String branch;
	
	private String minorIndicator;
		
	private String createdBy;
	
	private LocalDateTime createdDate;

	private String modifiedBy;
	
	private LocalDateTime modifiedDate;

	private int version;
}
