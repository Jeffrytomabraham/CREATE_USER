package com.banking.account.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.banking.account.exception.DuplicateUserException;
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
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode(ValidationMessages.DUPLICATE_USER.getCode());
			error.setMessage(ex.getMessage());
			AccountDetailsDTO accountDetails = new AccountDetailsDTO();
			accountDetails.setHttpStatus(HttpStatus.BAD_REQUEST);
			accountDetails.setErrorResponse(error);
			return accountDetails;
		}
	}
}
