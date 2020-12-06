package com.omnirio.customer.service;

import com.omnirio.customer.dto.RoleDto;

public interface RoleService {

	RoleDto findByName(String name);
}
