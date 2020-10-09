package com.banking.account.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banking.account.request.dto.UserDetailsDTO;
import com.banking.account.response.dto.AccountDetailsDTO;
import com.banking.account.service.AccountCreationService;

@Component
public class AccountCreationComponent {

	private Log log = LogFactory.getLog(AccountCreationComponent.class);
	
	@Autowired
	AccountCreationService accountCreationService;
	
	public AccountDetailsDTO createAccount(UserDetailsDTO userDetails) {
		log.info("Entering AccountCreationComponent.createAccount ");
		return accountCreationService.createAccount(userDetails);
	}
}
