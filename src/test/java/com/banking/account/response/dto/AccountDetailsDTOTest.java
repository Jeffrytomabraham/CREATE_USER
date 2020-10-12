package com.banking.account.response.dto;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountDetailsDTOTest {

    private AccountDetailsDTO accountDetailsDTOUnderTest;

    @Before
    public void setUp() {
        accountDetailsDTOUnderTest = new AccountDetailsDTO();
    }
    
    @Test
    public void setAccountDetails() {
    	accountDetailsDTOUnderTest.setAddress1("temp1");
        accountDetailsDTOUnderTest.setAddress2("temp1");
        accountDetailsDTOUnderTest.setAge(10);
        accountDetailsDTOUnderTest.setCountry("India");
        accountDetailsDTOUnderTest.setCity("tvm");
        accountDetailsDTOUnderTest.setDob("02-02-2020");
        accountDetailsDTOUnderTest.setEmail("abc@abc.com");
        accountDetailsDTOUnderTest.setUserName("username");
        accountDetailsDTOUnderTest.setPostalCode("698754");
        accountDetailsDTOUnderTest.setFirstName("firstname");
        accountDetailsDTOUnderTest.setLastName("lastname");
        accountDetailsDTOUnderTest.setPhone("123654789");
        accountDetailsDTOUnderTest.setErrorResponse(new ErrorResponse());
        AccountsDTO account = new AccountsDTO();
        account.setAccountNumber("123");
        account.setAccountType("SAVINGS");
        account.setBalance(1000);
        List<AccountsDTO> accountList = new ArrayList<>();
        accountList.add(account);
        accountDetailsDTOUnderTest.setAccounts(accountList);

        Assert.assertEquals(accountDetailsDTOUnderTest.getUserName(), "username");
        Assert.assertEquals(accountDetailsDTOUnderTest.getFirstName(), "firstname");
        Assert.assertEquals(accountDetailsDTOUnderTest.getLastName(), "lastname");
        Assert.assertEquals(accountDetailsDTOUnderTest.getPhone(), "123654789");
        Assert.assertEquals(accountDetailsDTOUnderTest.getPostalCode(), "698754");
        Assert.assertTrue(accountDetailsDTOUnderTest.getAccounts().size()>0);
        Assert.assertEquals(accountDetailsDTOUnderTest.getAddress1(), "temp1");
        Assert.assertEquals(accountDetailsDTOUnderTest.getAddress2(), "temp1");
        Assert.assertTrue(accountDetailsDTOUnderTest.getAge()==10);
        Assert.assertEquals(accountDetailsDTOUnderTest.getCity(), "tvm");
        Assert.assertEquals(accountDetailsDTOUnderTest.getCountry(), "India");
        Assert.assertEquals(accountDetailsDTOUnderTest.getDob(), "02-02-2020");
        Assert.assertEquals(accountDetailsDTOUnderTest.getEmail(), "abc@abc.com");
        Assert.assertNotNull(accountDetailsDTOUnderTest.getErrorResponse());
    }
}
