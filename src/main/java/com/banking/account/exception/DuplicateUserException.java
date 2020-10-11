package com.banking.account.exception;

public class DuplicateUserException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DuplicateUserException(String s) 
    {  
        super(s); 
    } 
}
