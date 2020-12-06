package com.omnirio.customer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omnirio.customer.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

	Role findRoleByRoleName(String name);
}
