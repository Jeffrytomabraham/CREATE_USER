package com.banking.account.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.banking.account.component.AccountCreationComponent;
import com.banking.account.request.dto.UserDetailsDTO;
import com.banking.account.response.dto.AccountDetailsDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/banking/user/")
public class BankAccountCreationController {
	
	private Log log = LogFactory.getLog(BankAccountCreationController.class);

	@Autowired
	AccountCreationComponent accountCreationComponent;
	
	
	@RequestMapping(value= {"/create/account"},method = RequestMethod.POST)
	@ApiOperation(value = "Create customer account")
	public @ResponseBody ResponseEntity<?> createUserAccount(@Valid @RequestBody UserDetailsDTO userDetailsDTO){
		log.info("Create user account");
		log.debug("Create user account for -"+userDetailsDTO.getUserName());
		AccountDetailsDTO accountDetailsDTO =accountCreationComponent.createAccount(userDetailsDTO);
		HttpStatus httpStatus = HttpStatus.OK;
		if(accountDetailsDTO.getErrorResponse()!=null) {
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		accountDetailsDTO.setHttpStatus(httpStatus);
		return new ResponseEntity<>(accountDetailsDTO,HttpStatus.OK);
	}
	
//	@RequestMapping(value= {"/auth/user"},method = RequestMethod.POST)
//	public @ResponseBody ResponseEntity<?> UserAuthentication(@Valid @RequestBody UserAuthenticationDTO userAuthenticationDTO){
//		
//	}
}
