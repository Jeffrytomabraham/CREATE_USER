package com.banking.account.dao;

import com.banking.account.Entity.UserDetailsEntityDTO;

public interface AccountCreationDAO {

	public UserDetailsEntityDTO createAccount(UserDetailsEntityDTO userDetails);
	
	public UserDetailsEntityDTO findByUserName(String username);
}
