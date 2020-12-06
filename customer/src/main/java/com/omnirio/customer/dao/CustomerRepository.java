package com.omnirio.customer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omnirio.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

	Customer findByUsername(String userName);
}
