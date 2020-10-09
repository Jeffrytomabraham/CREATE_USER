package com.banking.account.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.banking.account.Entity.dto.UserDetailsEntityDTO;
import com.banking.account.dao.AccountCreationDAO;

@Component
public class AccountCreationDAOImpl implements AccountCreationDAO{

	private Log log = LogFactory.getLog(AccountCreationDAOImpl.class);
			
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public UserDetailsEntityDTO createAccount(UserDetailsEntityDTO userDetails) {
		log.info("Saving user data");
		log.debug("Entering AccountCreationDAOImpl.createAccount for saving user -"+userDetails.getUserName());
		
		UserDetailsEntityDTO savedUserDetails= mongoTemplate.save(userDetails);
		
		log.debug("Exiting AccountCreationDAOImpl.createAccount after saving user -"+userDetails.getUserName());
		log.info("User data saved");
		return savedUserDetails;
	}
	
	public boolean findByUserName(UserDetailsEntityDTO userDetails) {
		log.info("Entering AccountCreationDAOImpl.findByUserName - ");
		log.debug("Enetring AccountCreationDAOImpl.findByUserName for checking duplicate user -"+userDetails.getUserName());
		
		Query query = new Query();
		query.addCriteria(Criteria.where("userName").is(userDetails.getUserName()));
		List<UserDetailsEntityDTO> savedUserDetails= mongoTemplate.find(query, UserDetailsEntityDTO.class);
		if(savedUserDetails.size()>0) {
			log.debug("Duplicate record for user in AccountCreationDAOImpl.findByUserName -"+userDetails.getUserName());
			
			return true;
		} else {
			log.debug("Not a duplicate user -"+userDetails.getUserName());
			return false;
		}
		
		
	}
}
