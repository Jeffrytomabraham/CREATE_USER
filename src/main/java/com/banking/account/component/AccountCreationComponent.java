package com.banking.account.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banking.account.exception.DuplicateUserException;
import com.banking.account.exception.UserDoesNotExistsException;
import com.banking.account.request.dto.AddAccountDTO;
import com.banking.account.request.dto.UserDetailsDTO;
import com.banking.account.response.dto.AccountDetailsDTO;
import com.banking.account.response.dto.ErrorResponse;
import com.banking.account.service.AccountCreationService;
import com.banking.account.util.ValidationMessages;

@Component
public class AccountCreationComponent {

	private Log log = LogFactory.getLog(AccountCreationComponent.class);
	
	@Autowired
	AccountCreationService accountCreationService;
	
	public AccountDetailsDTO createAccount(UserDetailsDTO userDetails) {
		log.info("Entering AccountCreationComponent.createAccount ");
		try {
			return accountCreationService.createAccount(userDetails);
		} catch(DuplicateUserException ex) {
			log.info("DuplicateUserException in AccountCreationComponent.createAccount ");
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode(ValidationMessages.DUPLICATE_USER.getCode());
			error.setMessage(ex.getMessage());
			AccountDetailsDTO accountDetails = new AccountDetailsDTO();
			accountDetails.setErrorResponse(error);
			log.info("Exiting AccountCreationComponent.createAccount ");
			return accountDetails;
		}
	}
	
	public AccountDetailsDTO addAccount(AddAccountDTO addAccountDTO) {
		log.info("Entering AccountCreationComponent.addAccount ");
		try {
			return accountCreationService.addAccount(addAccountDTO);
		} catch(UserDoesNotExistsException ex) {
			log.info("UserDoesNotExistsException in AccountCreationComponent.addAccount ");
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode(ValidationMessages.INVALID_USER.getCode());
			error.setMessage(ex.getMessage());
			AccountDetailsDTO accountDetails = new AccountDetailsDTO();
			accountDetails.setErrorResponse(error);
			log.info("Exiting AccountCreationComponent.addAccount with UserDoesNotExistsException");
			return accountDetails;
		}	
	}
}
