package com.banking.account.Entity;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.banking.account.entity.UserDetailsEntityDTO;
import com.banking.account.response.dto.AccountsDTO;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsEntityDTOTest {

    private UserDetailsEntityDTO userDetailsEntityDTOUnderTest;

    @BeforeEach
    public void setUp() {
        userDetailsEntityDTOUnderTest = new UserDetailsEntityDTO();
    }

    @Test
    public void setUserDetails(){
        userDetailsEntityDTOUnderTest.setAddress1("temp1");
        userDetailsEntityDTOUnderTest.setAddress2("temp1");
        userDetailsEntityDTOUnderTest.setAge(10);
        userDetailsEntityDTOUnderTest.setCountry("India");
        userDetailsEntityDTOUnderTest.setCity("tvm");
        userDetailsEntityDTOUnderTest.setDob("02-02-2020");
        userDetailsEntityDTOUnderTest.setEmail("abc@abc.com");
        userDetailsEntityDTOUnderTest.setUserName("username");
        userDetailsEntityDTOUnderTest.setPostalCode("698754");
        userDetailsEntityDTOUnderTest.setFirstName("firstname");
        userDetailsEntityDTOUnderTest.setLastName("lastname");
        userDetailsEntityDTOUnderTest.setPhone("123654789");
        userDetailsEntityDTOUnderTest.setPassword("123654789");
        userDetailsEntityDTOUnderTest.setId("123654789");
        AccountsDTO account = new AccountsDTO();
        account.setAccountNumber("123");
        account.setAccountType("SAVINGS");
        account.setBalance(1000);
        List<AccountsDTO> accountList = new ArrayList<>();
        accountList.add(account);
        userDetailsEntityDTOUnderTest.setAccounts(accountList);

        Assert.assertEquals(userDetailsEntityDTOUnderTest.getUserName(), "username");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getFirstName(), "firstname");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getLastName(), "lastname");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getPhone(), "123654789");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getPostalCode(), "698754");
        Assert.assertTrue(userDetailsEntityDTOUnderTest.getAccounts().size()>0);
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getAddress1(), "temp1");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getAddress2(), "temp1");
        Assert.assertTrue(userDetailsEntityDTOUnderTest.getAge()==10);
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getCity(), "tvm");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getCountry(), "India");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getDob(), "02-02-2020");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getEmail(), "abc@abc.com");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getPassword(), "123654789");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getId(), "123654789");
    }
}
