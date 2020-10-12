package com.banking.account.service.impl;

import com.banking.account.Entity.UserDetailsEntityDTO;
import com.banking.account.dao.AccountCreationDAO;
import com.banking.account.exception.DuplicateUserException;
import com.banking.account.exception.UserDoesNotExistsException;
import com.banking.account.request.dto.AddAccountDTO;
import com.banking.account.request.dto.UserDetailsDTO;
import com.banking.account.response.dto.AccountDetailsDTO;
import com.banking.account.response.dto.AccountsDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountCreationServiceImplTest {

    private AccountCreationServiceImpl accountCreationServiceImplUnderTest;
    private UserDetailsEntityDTO userDetailsEntityDTO;

    @Before
    public void setUp() {
        accountCreationServiceImplUnderTest = new AccountCreationServiceImpl();
        accountCreationServiceImplUnderTest.accountCreationDAO = mock(AccountCreationDAO.class);
        userDetailsEntityDTO= new UserDetailsEntityDTO();
        userDetailsEntityDTO.setAddress1("temp1");
        userDetailsEntityDTO.setAddress2("temp1");
        userDetailsEntityDTO.setAge(10);
        userDetailsEntityDTO.setCountry("India");
        userDetailsEntityDTO.setCity("tvm");
        userDetailsEntityDTO.setDob("02-02-2020");
        userDetailsEntityDTO.setEmail("abc@abc.com");
        userDetailsEntityDTO.setUserName("username");
        userDetailsEntityDTO.setPostalCode("698754");
        userDetailsEntityDTO.setFirstName("firstname");
        userDetailsEntityDTO.setLastName("lastname");
        userDetailsEntityDTO.setPhone("123654789");
        userDetailsEntityDTO.setPassword("123654789");
        userDetailsEntityDTO.setId("123654789");
        AccountsDTO account = new AccountsDTO();
        account.setAccountNumber("123");
        account.setAccountType("SAVINGS");
        account.setBalance(1000);
        List<AccountsDTO> accountList = new ArrayList<>();
        accountList.add(account);
        userDetailsEntityDTO.setAccounts(accountList);
    }

    @Test
    public void testCreateAccount() {
        final UserDetailsDTO userDetails = new UserDetailsDTO();

        when(accountCreationServiceImplUnderTest.accountCreationDAO.findByUserName("username")).thenReturn(userDetailsEntityDTO);

        when(accountCreationServiceImplUnderTest.accountCreationDAO.createAccount(any(UserDetailsEntityDTO.class))).thenReturn(userDetailsEntityDTO);

        final AccountDetailsDTO result = accountCreationServiceImplUnderTest.createAccount(userDetails);

        Assert.assertEquals(userDetailsEntityDTO.getUserName(), "username");
        Assert.assertEquals(userDetailsEntityDTO.getFirstName(), "firstname");
        Assert.assertEquals(userDetailsEntityDTO.getLastName(), "lastname");
        Assert.assertEquals(userDetailsEntityDTO.getPhone(), "123654789");
        Assert.assertEquals(userDetailsEntityDTO.getPostalCode(), "698754");
        Assert.assertTrue(userDetailsEntityDTO.getAccounts().size()>0);
        Assert.assertEquals(userDetailsEntityDTO.getAddress1(), "temp1");
        Assert.assertEquals(userDetailsEntityDTO.getAddress2(), "temp1");
        Assert.assertTrue(userDetailsEntityDTO.getAge()==10);
        Assert.assertEquals(userDetailsEntityDTO.getCity(), "tvm");
        Assert.assertEquals(userDetailsEntityDTO.getCountry(), "India");
        Assert.assertEquals(userDetailsEntityDTO.getDob(), "02-02-2020");
        Assert.assertEquals(userDetailsEntityDTO.getEmail(), "abc@abc.com");
        Assert.assertEquals(userDetailsEntityDTO.getPassword(), "123654789");
        Assert.assertEquals(userDetailsEntityDTO.getId(), "123654789");
    }

    @Test(expected = DuplicateUserException.class)
    public void testCreateAccount_ThrowsDuplicateUserException() {
        final UserDetailsDTO userDetails = new UserDetailsDTO();

        when(accountCreationServiceImplUnderTest.accountCreationDAO.findByUserName("username")).thenReturn(userDetailsEntityDTO);

        when(accountCreationServiceImplUnderTest.accountCreationDAO.createAccount(any(UserDetailsEntityDTO.class))).thenReturn(userDetailsEntityDTO);

        accountCreationServiceImplUnderTest.createAccount(userDetails);
    }

    @Test
    public void testAddAccount() {
        final AddAccountDTO addAccountDTO = new AddAccountDTO();

        final UserDetailsEntityDTO userDetailsEntityDTO = new UserDetailsEntityDTO();
        when(accountCreationServiceImplUnderTest.accountCreationDAO.findByUserName("username")).thenReturn(userDetailsEntityDTO);

        final UserDetailsEntityDTO userDetailsEntityDTO1 = new UserDetailsEntityDTO();
        when(accountCreationServiceImplUnderTest.accountCreationDAO.createAccount(any(UserDetailsEntityDTO.class))).thenReturn(userDetailsEntityDTO1);

        final AccountDetailsDTO result = accountCreationServiceImplUnderTest.addAccount(addAccountDTO);

        Assert.assertEquals(userDetailsEntityDTO.getUserName(), "username");
        Assert.assertEquals(userDetailsEntityDTO.getFirstName(), "firstname");
        Assert.assertEquals(userDetailsEntityDTO.getLastName(), "lastname");
        Assert.assertEquals(userDetailsEntityDTO.getPhone(), "123654789");
        Assert.assertEquals(userDetailsEntityDTO.getPostalCode(), "698754");
        Assert.assertTrue(userDetailsEntityDTO.getAccounts().size()>0);
        Assert.assertEquals(userDetailsEntityDTO.getAddress1(), "temp1");
        Assert.assertEquals(userDetailsEntityDTO.getAddress2(), "temp1");
        Assert.assertTrue(userDetailsEntityDTO.getAge()==10);
        Assert.assertEquals(userDetailsEntityDTO.getCity(), "tvm");
        Assert.assertEquals(userDetailsEntityDTO.getCountry(), "India");
        Assert.assertEquals(userDetailsEntityDTO.getDob(), "02-02-2020");
        Assert.assertEquals(userDetailsEntityDTO.getEmail(), "abc@abc.com");
        Assert.assertEquals(userDetailsEntityDTO.getPassword(), "123654789");
        Assert.assertEquals(userDetailsEntityDTO.getId(), "123654789");
    }

    @Test(expected = UserDoesNotExistsException.class)
    public void testAddAccount_ThrowsUserDoesNotExistsException() {
       
        final AddAccountDTO addAccountDTO = new AddAccountDTO();

        final UserDetailsEntityDTO userDetailsEntityDTO = new UserDetailsEntityDTO();
        when(accountCreationServiceImplUnderTest.accountCreationDAO.findByUserName("username")).thenReturn(userDetailsEntityDTO);

        final UserDetailsEntityDTO userDetailsEntityDTO1 = new UserDetailsEntityDTO();
        when(accountCreationServiceImplUnderTest.accountCreationDAO.createAccount(any(UserDetailsEntityDTO.class))).thenReturn(userDetailsEntityDTO1);

        accountCreationServiceImplUnderTest.addAccount(addAccountDTO);
    }
}
