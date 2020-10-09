package com.banking.account.service;

import com.banking.account.request.dto.UserDetailsDTO;
import com.banking.account.response.dto.AccountDetailsDTO;

public interface AccountCreationService {

	public AccountDetailsDTO createAccount(UserDetailsDTO userDetails);
}
