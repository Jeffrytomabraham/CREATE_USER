package com.banking.account.response.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountsDTOTest {

    private AccountsDTO accountsDTOUnderTest;

    @BeforeEach
    public void setUp() {
        accountsDTOUnderTest = new AccountsDTO();
    }

    @Test
    public void setAccountsDTO(){
    	LocalDateTime dateTime = LocalDateTime.now();
        AccountsDTO account = new AccountsDTO();
        account.setAccountNumber("123");
        account.setAccountType("SAVINGS");
        account.setBalance(1000);
        account.setCreationDate(dateTime);
        account.setDueAmount(0);
        
        assertEquals(account.getAccountNumber(),"123");
        assertEquals(account.getAccountType(),"SAVINGS");
        assertTrue(account.getBalance()==1000);
        assertEquals(account.getCreationDate(),dateTime);
        assertTrue(account.getDueAmount()==0);
    }
}
