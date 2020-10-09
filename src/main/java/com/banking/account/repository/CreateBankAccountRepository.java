package com.banking.account.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.banking.account.Entity.dto.UserDetailsEntityDTO;

@Repository
public interface CreateBankAccountRepository extends MongoRepository<UserDetailsEntityDTO, String>{

	public UserDetailsEntityDTO createAccount(UserDetailsEntityDTO userDetails);
}
