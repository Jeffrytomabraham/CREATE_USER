package com.banking.account.util;

public enum ValidationMessages {

	  DUPLICATE_USER("DUPLICATE_USER", "This user already exists."),
	  CONSTRAIN_VIOLATION("CONSTRAIN_VIOLATION", "Fiels constrain violation");

	  private final String code;
	  private final String description;

	  private ValidationMessages(String code, String description) {
	    this.code = code;
	    this.description = description;
	  }

	  public String getDescription() {
	     return description;
	  }

	  public String getCode() {
	     return code;
	  }

	  @Override
	  public String toString() {
	    return code + ": " + description;
	  }
}
