package com.omnirio.customer.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.omnirio.customer.dao.CustomerRepository;
import com.omnirio.customer.dto.CustomerDto;
import com.omnirio.customer.dto.RoleDto;
import com.omnirio.customer.entity.Customer;
import com.omnirio.customer.entity.Role;
import com.omnirio.customer.mapper.CustomerMapper;
import com.omnirio.customer.mapper.RoleMapper;
import com.omnirio.customer.service.CustomerService;
import com.omnirio.customer.service.RoleService;

@Service
public class CustomerServiceImpl implements CustomerService
//, UserDetailsService 
{
	
	@Autowired
	CustomerRepository customerRepository;
	
//	@Autowired
//	BCryptPasswordEncoder encoder;
	
	@Autowired
	CustomerMapper customerMapper;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	RoleMapper roleMapper;

	@Override
	public CustomerDto save(CustomerDto customerDto) {
		Customer customer = customerMapper.customerDtoToCustomer(customerDto);
		//customer.setPassword(encoder.encode(customer.getPassword()));
		
		RoleDto roleDto = roleService.findByName("USER");
		Set<Role> roleSet = new HashSet<>();
		roleSet.add(roleMapper.roleDtoToRole(roleDto));
		
		if(customer.getUsername().contains("manager")) {
			RoleDto branchManagerDto = roleService.findByName("BRANCH_MANAGER");
			roleSet.add(roleMapper.roleDtoToRole(branchManagerDto));
		}
		
		//customer.setRoles(roleSet);
		
		return customerMapper.customerToCustomerDto(customerRepository.save(customer));
	}

//	@Override
//	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//		Customer customer = customerRepository.findByUsername(userName);
//		if(null == customer) {
//			throw new UsernameNotFoundException("Invalid username and password");
//		}
//		
//		return new User(customer.getUsername(), customer.getPassword(), getAuthority(customer));
//	}
	
//	private Set<SimpleGrantedAuthority> getAuthority(Customer user) {
//        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//        user.getRoles().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
//        });
//        return authorities;
//    }

	@Override
	public CustomerDto findByUsername(String userName) {
		Customer customer = customerRepository.findByUsername(userName);
		if(null != customer) {
			return customerMapper.customerToCustomerDto(customer);
		}
		return null;
	}

	@Override
	public CustomerDto updateCustomer(CustomerDto customerDto) {
		return customerMapper.customerToCustomerDto(customerRepository.save(customerMapper.customerDtoToCustomer(customerDto)));
	}

	@Override
	public CustomerDto findByUserId(String userId) {
		Optional<Customer> customer = customerRepository.findById(userId);
		if(customer.isPresent()) {
			return customerMapper.customerToCustomerDto(customer.get());
		}
		return null;
	}

	@Override
	public List<CustomerDto> findAll() {
		return customerMapper.customerListToCustomerDtoList(customerRepository.findAll());
	}

}
