package com.banking.account.request.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddAccountDTO {

	@NotNull(message="userName should not be null")
	@NotBlank(message="userName should not be empty")
	private String userName;
	@NotNull(message="accountType should not be null")
	@NotBlank(message="accountType should not be empty")
	private String accountType;
	private double creditAmount;
}
