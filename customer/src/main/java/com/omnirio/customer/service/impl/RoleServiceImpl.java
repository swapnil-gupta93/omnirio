package com.omnirio.customer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omnirio.customer.dao.RoleRepository;
import com.omnirio.customer.dto.RoleDto;
import com.omnirio.customer.mapper.RoleMapper;
import com.omnirio.customer.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	RoleMapper roleMapper;

	@Override
	public RoleDto findByName(String name) {
		return roleMapper.roleToRoleDto(roleRepository.findRoleByRoleName(name));
	}
	
	

}
