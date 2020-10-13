package com.banking.account.service.impl;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.banking.account.dao.AccountCreationDAO;
import com.banking.account.entity.UserDetailsEntityDTO;
import com.banking.account.exception.DuplicateUserException;
import com.banking.account.exception.UserDoesNotExistsException;
import com.banking.account.request.dto.AddAccountDTO;
import com.banking.account.request.dto.UserDetailsDTO;
import com.banking.account.response.dto.AccountDetailsDTO;
import com.banking.account.response.dto.AccountsDTO;
import com.banking.account.service.AccountCreationService;
import com.banking.account.util.ValidationMessages;

@Service
public class AccountCreationServiceImpl implements AccountCreationService{

	private Log log = LogFactory.getLog(AccountCreationServiceImpl.class);
			
	@Autowired
	AccountCreationDAO accountCreationDAO;
	
    ModelMapper modelMapper = new ModelMapper();
	
	public AccountDetailsDTO createAccount(UserDetailsDTO userDetails) throws DuplicateUserException{
		log.info("Entering AccountCreationServiceImpl.createAccount");
		log.debug("Entering AccountCreationServiceImpl.createAccount");
		UserDetailsEntityDTO userDetailsEntity = modelMapper.map(userDetails, UserDetailsEntityDTO.class);
		
		int accountNumber = randomNumberGenerator();
		AccountsDTO account = new AccountsDTO();
		account.setAccountNumber(String.valueOf(accountNumber));
		account.setAccountType(userDetails.getAccountType());
		account.setBalance(userDetails.getCreditAmount());
		account.setCreationDate(LocalDateTime.now());
		account.setDueAmount(0);
		
		int strength = 10;
		BCryptPasswordEncoder bCryptPasswordEncoder =
				  new BCryptPasswordEncoder(strength, new SecureRandom());
		String encodedPassword = bCryptPasswordEncoder.encode(userDetails.getPassword());
		userDetailsEntity.setPassword(encodedPassword);
		userDetailsEntity.setAccounts(Arrays.asList(account));
		log.debug("Setting entity class for create account");
		UserDetailsEntityDTO userDetailsEntityDto = accountCreationDAO.findByUserName(userDetailsEntity.getUserName());
		if(userDetailsEntityDto!=null){
			throw new DuplicateUserException(ValidationMessages.DUPLICATE_USER.getDescription());
		} 
		log.debug("No duplicate entry for user - "+userDetailsEntity.getUserName());
		userDetailsEntity = accountCreationDAO.createAccount(userDetailsEntity);
		log.debug("User Created and sending back response ");
		log.debug("Exiting AccountCreationServiceImpl.createAccount");
		log.info("Exiting AccountCreationServiceImpl.createAccount");
		return modelMapper.map(userDetailsEntity, AccountDetailsDTO.class);
	}
	
	public AccountDetailsDTO addAccount(AddAccountDTO addAccountDTO) throws UserDoesNotExistsException{
		log.info("Entering AccountCreationServiceImpl.addAccountDTO");
		log.debug("Entering AccountCreationServiceImpl.addAccountDTO");
		UserDetailsEntityDTO userDetailsEntityDto = accountCreationDAO.findByUserName(addAccountDTO.getUserName());
		if(userDetailsEntityDto ==null) {
			log.debug("User not found exception for user "+addAccountDTO.getUserName());
			throw new UserDoesNotExistsException(ValidationMessages.INVALID_USER.getDescription());
		}
		
		int accountNumber = randomNumberGenerator();
		AccountsDTO account = new AccountsDTO();
		account.setAccountNumber(String.valueOf(accountNumber));
		account.setAccountType(addAccountDTO.getAccountType());
		account.setBalance(addAccountDTO.getCreditAmount());
		account.setCreationDate(LocalDateTime.now());
		account.setDueAmount(0);
		List<AccountsDTO> accounts = new ArrayList<>();
		accounts.addAll(userDetailsEntityDto.getAccounts());
		accounts.add(account);
		userDetailsEntityDto.setAccounts(accounts);
		userDetailsEntityDto = accountCreationDAO.createAccount(userDetailsEntityDto);
		log.debug("User account added Created ");
		log.debug("Exiting AccountCreationServiceImpl.addAccountDTO");
		log.info("Exiting AccountCreationServiceImpl.addAccountDTO");
		return modelMapper.map(userDetailsEntityDto, AccountDetailsDTO.class);
	}
	
	private int randomNumberGenerator() {
		Random rand = new Random();
		return rand.nextInt(10000);
	}
}
