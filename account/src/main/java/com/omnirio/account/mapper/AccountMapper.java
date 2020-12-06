package com.omnirio.account.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.omnirio.account.dto.AccountDto;
import com.omnirio.account.entity.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {

	AccountDto accountToAccountDto(Account account);
	
	Account accountDtoToAccount(AccountDto accountDto);
	
	List<AccountDto> accountListToAccountDtoList(List<Account> account);
}
