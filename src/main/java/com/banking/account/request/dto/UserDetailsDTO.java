package com.banking.account.request.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class UserDetailsDTO {

	@NotNull(message="first name should not be null")
	@NotBlank
	private String firstName;
	
	@NotNull(message="last name should not be null")
	@NotBlank
	private String lastName;
	
	@NotBlank
	@NotNull(message="username should not be null")
	private String userName;
	
	@NotBlank
	@NotNull(message="dob should not be null")
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	private String dob;
	
	@NumberFormat
	@Range(min = 1,max = 150)
	private int age;
	
	@Email
	@NotNull
	private String email;
	
	@NotBlank
	@NotNull(message="address1 should not be null")
	private String address1;
	
	private String address2;
	
	@NotBlank
	@NotNull(message="postalCode should not be null")
	private String postalCode;
	
	@NotBlank
	@NotNull(message="country should not be null")
	private String country;
	
	@NotBlank
	@NotNull(message="city should not be null")
	private String city;
	
	@NumberFormat
	private Number phone;
	
	@NotBlank
	@NotNull(message="password should not be null")
	private String password;
	
	@NotBlank
	@NotNull(message="accountType should not be null")
	private String accountType;
	
	private long creditAmount;
}
