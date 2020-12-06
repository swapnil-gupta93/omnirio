package com.omnirio.customer.mapper;

import org.mapstruct.Mapper;

import com.omnirio.customer.dto.RoleDto;
import com.omnirio.customer.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

	RoleDto roleToRoleDto(Role role);
	
	Role roleDtoToRole(RoleDto roleDto);
}
