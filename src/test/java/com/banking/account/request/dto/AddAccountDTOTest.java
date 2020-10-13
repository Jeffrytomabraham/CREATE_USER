package com.banking.account.request.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AddAccountDTOTest {

    private AddAccountDTO addAccountDTOUnderTest;

    @BeforeEach
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
