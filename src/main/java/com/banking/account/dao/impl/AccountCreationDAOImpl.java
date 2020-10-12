package com.banking.account.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.banking.account.Entity.UserDetailsEntityDTO;
import com.banking.account.dao.AccountCreationDAO;

@Component
public class AccountCreationDAOImpl implements AccountCreationDAO{

	private Log log = LogFactory.getLog(AccountCreationDAOImpl.class);
			
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public UserDetailsEntityDTO createAccount(UserDetailsEntityDTO userDetails) {
		log.info("Entering AccountCreationDAOImpl.createAccount");
		log.debug("Entering AccountCreationDAOImpl.createAccount for saving user -"+userDetails.getUserName());
		
		UserDetailsEntityDTO savedUserDetails= mongoTemplate.save(userDetails);
		
		log.debug("Exiting AccountCreationDAOImpl.createAccount after saving user -"+userDetails.getUserName());
		log.info("Exiting AccountCreationDAOImpl.createAccount");
		return savedUserDetails;
	}
	
	public UserDetailsEntityDTO findByUserName(String username) {
		log.info("Entering AccountCreationDAOImpl.findByUserName  ");
		log.debug("Enetring AccountCreationDAOImpl.findByUserName for checking duplicate user -"+username);
		
		Query query = new Query();
		query.addCriteria(Criteria.where("userName").is(username));
		List<UserDetailsEntityDTO> savedUserDetails= mongoTemplate.find(query, UserDetailsEntityDTO.class);
		log.info("Exiting AccountCreationDAOImpl.findByUserName  ");
		log.debug("Exiting AccountCreationDAOImpl.findByUserName after checking duplicate user -"+username);
		return savedUserDetails.get(0);
	}
}
