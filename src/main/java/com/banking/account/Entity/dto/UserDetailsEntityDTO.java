package com.banking.account.Entity.dto;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.banking.account.response.dto.AccountsDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Document
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsEntityDTO {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String userName;
	private String dob;
	private int age;
	private String email;
	private String address1;
	private String address2;
	private String postalCode;
	private String country;
	private String city;
	private Number phone;
	private String password;
	private List<AccountsDTO> accounts;
}
