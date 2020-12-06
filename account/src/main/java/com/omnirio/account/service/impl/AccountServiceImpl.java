package com.omnirio.account.service.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.omnirio.account.dao.AccountRepository;
import com.omnirio.account.dto.AccountCustomerDto;
import com.omnirio.account.dto.AccountDto;
import com.omnirio.account.dto.CustomerDto;
import com.omnirio.account.dto.ResponseDto;
import com.omnirio.account.entity.Account;
import com.omnirio.account.mapper.AccountMapper;
import com.omnirio.account.service.AccountService;
import com.thoughtworks.xstream.converters.time.LocalDateTimeConverter;

import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	AccountMapper accountMapper;
	
	@Autowired
	EurekaClient eurekaClient;
	
	private final WebClient webclient;
	
	public AccountServiceImpl() {
		this.webclient = WebClient.builder()
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		String url = getCustomerUrl();
		ResponseDto responseDto = webclient
		.get().uri(url.concat("/customer/user/").concat(accountDto.getCustomerId()))
		.retrieve()
		.bodyToMono(ResponseDto.class)
		.block();
		
		if(responseDto.isSuccess()) {
			LinkedHashMap<String, Object> respMap = (LinkedHashMap<String, Object>) responseDto.getData();
			String dateOfBirth = (String) respMap.get("dob");
			LocalDateTime date = LocalDateTime.parse(dateOfBirth);
			accountDto.setMinorIndicator(getMinorIndicator(date));			
		}
		
		return accountMapper.accountToAccountDto(accountRepository.save(accountMapper.accountDtoToAccount(accountDto)));
	}

	@Override
	public AccountDto getAccountById(String accountId) {
		Optional<Account> account = accountRepository.findById(accountId);
		if(account.isPresent()) {
			return accountMapper.accountToAccountDto(account.get());
		}
		
		return null;
	}

	@Override
	public boolean deleteAccount(String accountId) {
		if(accountRepository.existsById(accountId)) {
			accountRepository.deleteById(accountId);
			return true;
		}
		return false;
	}
	
	private String getCustomerUrl() {
		Application application = eurekaClient.getApplication("customer");
		InstanceInfo instanceInfo = application.getInstances().get(0);
		return instanceInfo.getHomePageUrl();
		
	}
	
	private String getMinorIndicator(LocalDateTime dateOfBirth) {
		long years = ChronoUnit.YEARS.between(LocalDateTime.now(), dateOfBirth);
		if(years >= 18) {
			return "Y";
		}
		else
			return "N";
	}

	@Override
	public AccountDto createCustomerAccount(AccountCustomerDto accountCustomerDto) {
		String url = getCustomerUrl();
		
		ResponseDto responseDto = webclient
				.post().uri(url.concat("/customer/register"))
				.body(Mono.just(accountCustomerDto.getCustomer()), CustomerDto.class)
				.retrieve()
				.bodyToMono(ResponseDto.class)
				.block();
		
		if(responseDto.isSuccess()) {
			LinkedHashMap<String, Object> respMap = (LinkedHashMap<String, Object>) responseDto.getData();
			String userId = (String) respMap.get("userId");
			AccountDto accountDto = accountCustomerDto.getAccount();
			accountDto.setCustomerId(userId);
			accountDto.setMinorIndicator(getMinorIndicator(accountCustomerDto.getCustomer().getDob()));
			
			return accountMapper.accountToAccountDto(accountRepository.save(accountMapper.accountDtoToAccount(accountDto)));
		}
		
		return null;
	}

	@Override
	public List<AccountDto> findAll() {
		return accountMapper.accountListToAccountDtoList(accountRepository.findAll());
	}

}
