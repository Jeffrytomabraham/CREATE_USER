package com.banking.account.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.banking.account.component.AccountCreationComponent;
import com.banking.account.request.dto.AddAccountDTO;
import com.banking.account.request.dto.UserDetailsDTO;
import com.banking.account.response.dto.AccountDetailsDTO;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/banking/user")
public class BankAccountCreationController {
	
	private Log log = LogFactory.getLog(BankAccountCreationController.class);

	@Autowired
	AccountCreationComponent accountCreationComponent;
	
	@PostMapping(value= {"/create/account"},consumes="application/json",produces="application/json")
	@ApiOperation(value = "Create customer account")
	public @ResponseBody ResponseEntity<?> createUserAccount(@Valid @RequestBody UserDetailsDTO userDetailsDTO){
		log.info("Entering BankAccountCreationController.createUserAccount");
		log.debug("Entering BankAccountCreationController.createUserAccount for user -"+userDetailsDTO.getUserName());
		AccountDetailsDTO accountDetailsDTO =accountCreationComponent.createAccount(userDetailsDTO);
		HttpStatus httpStatus = HttpStatus.OK;
		if(accountDetailsDTO.getErrorResponse()!=null) {
			log.info("Error while creating account");
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		log.info("Exiting BankAccountCreationController.createUserAccount");
		log.debug("Exiting BankAccountCreationController.createUserAccount");
		return new ResponseEntity<>(accountDetailsDTO,httpStatus);
	}
	
	@PostMapping(value= {"/add/account"},consumes="application/json",produces="application/json")
	@ApiOperation(value = "Create customer account")
	public @ResponseBody ResponseEntity<?> addUserAccount(@Valid @RequestBody AddAccountDTO addAccountDTO){
		log.info("Entering BankAccountCreationController.addUserAccount");
		log.debug("Entering BankAccountCreationController.addUserAccount for user "+addAccountDTO.getUserName());
		AccountDetailsDTO accountDetailsDTO =accountCreationComponent.addAccount(addAccountDTO);
		HttpStatus httpStatus = HttpStatus.OK;
		if(accountDetailsDTO.getErrorResponse()!=null) {
			log.info("Error while adding new account");
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		log.info("Exiting BankAccountCreationController.addUserAccount");
		log.debug("Exiting BankAccountCreationController.addUserAccount");
		return new ResponseEntity<>(accountDetailsDTO,httpStatus);
	}
}
