package com.banking.account.exception;

public class UserDoesNotExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UserDoesNotExistsException(String s) 
    {  
        super(s); 
    } 
}
