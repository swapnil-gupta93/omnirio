package com.omnirio.account.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omnirio.account.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

}
