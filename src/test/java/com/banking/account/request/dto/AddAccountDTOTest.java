package com.banking.account.request.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.mongodb.assertions.Assertions;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class AddAccountDTOTest {

    private AddAccountDTO addAccountDTOUnderTest;

    @Before
    public void setUp() {
        addAccountDTOUnderTest = new AddAccountDTO();
    }

    @Test
    public void setAddAccount(){
        addAccountDTOUnderTest.setAccountType("Savings");
        addAccountDTOUnderTest.setCreditAmount(100);
        addAccountDTOUnderTest.setUserName("username");
        
        assertEquals(addAccountDTOUnderTest.getAccountType(), "Savings");
        assertEquals(addAccountDTOUnderTest.getCreditAmount(), 100);
        assertEquals(addAccountDTOUnderTest.getUserName(), "username");
    }
}
