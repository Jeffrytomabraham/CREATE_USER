package com.banking.account.request.dto;

import com.banking.account.response.dto.ErrorResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserAuthenticationDTO {

	private String username;
	private String password;
	private ErrorResponse errorResponse;
}
