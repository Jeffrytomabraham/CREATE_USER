package com.banking.account.service;

import com.banking.account.exception.DuplicateUserException;
import com.banking.account.exception.UserDoesNotExistsException;
import com.banking.account.request.dto.AddAccountDTO;
import com.banking.account.request.dto.UserDetailsDTO;
import com.banking.account.response.dto.AccountDetailsDTO;

public interface AccountCreationService {

	public AccountDetailsDTO createAccount(UserDetailsDTO userDetails) throws DuplicateUserException;

	public AccountDetailsDTO addAccount(AddAccountDTO addAccountDTO) throws UserDoesNotExistsException;
}
