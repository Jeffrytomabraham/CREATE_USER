package com.banking.account.dao;

import com.banking.account.Entity.dto.UserDetailsEntityDTO;

public interface AccountCreationDAO {

	public UserDetailsEntityDTO createAccount(UserDetailsEntityDTO userDetails);
	
	public boolean findByUserName(UserDetailsEntityDTO userDetails);
}
