package com.banking.account.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.util.NestedServletException;

import com.banking.account.dao.AccountCreationDAO;
import com.banking.account.entity.UserDetailsEntityDTO;
import com.banking.account.exception.DuplicateUserException;
import com.banking.account.exception.UserDoesNotExistsException;
import com.banking.account.request.dto.AddAccountDTO;
import com.banking.account.request.dto.UserDetailsDTO;
import com.banking.account.response.dto.AccountDetailsDTO;
import com.banking.account.response.dto.AccountsDTO;

@RunWith(MockitoJUnitRunner.class)
public class AccountCreationServiceImplTest {

    private AccountCreationServiceImpl accountCreationServiceImplUnderTest;
    private UserDetailsEntityDTO userDetailsEntityDTO;
    private UserDetailsEntityDTO userDetailsEntityDTO1;

    @BeforeEach
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

        userDetailsEntityDTO1= new UserDetailsEntityDTO();
        userDetailsEntityDTO1.setAddress1("temp1");
        userDetailsEntityDTO1.setAddress2("temp1");
        userDetailsEntityDTO1.setAge(10);
        userDetailsEntityDTO1.setCountry("India");
        userDetailsEntityDTO1.setCity("tvm");
        userDetailsEntityDTO1.setDob("02-02-2020");
        userDetailsEntityDTO1.setEmail("abc@abc.com");
        userDetailsEntityDTO1.setUserName("username");
        userDetailsEntityDTO1.setPostalCode("698754");
        userDetailsEntityDTO1.setFirstName("firstname");
        userDetailsEntityDTO1.setLastName("lastname");
        userDetailsEntityDTO1.setPhone("123654789");
        userDetailsEntityDTO1.setPassword("123654789");
        userDetailsEntityDTO1.setId("123654789");
        userDetailsEntityDTO1.setAccounts(accountList);
    }

    @Test
    public void testCreateAccount() {
        UserDetailsDTO userDetails = new UserDetailsDTO();
        userDetails.setPassword("123654789");
        userDetails.setAccountType("Savings");
        userDetails.setAccountType("savings");
        userDetails.setCreditAmount(100);

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

    @Test
    public void testCreateAccount_ThrowsDuplicateUserException() {
        UserDetailsDTO userDetails = new UserDetailsDTO();
        userDetails.setPassword("123654789");
        userDetails.setAccountType("Savings");
        userDetails.setAccountType("savings");
        userDetails.setCreditAmount(100);

        when(accountCreationServiceImplUnderTest.accountCreationDAO.findByUserName(any())).thenReturn(userDetailsEntityDTO);

        when(accountCreationServiceImplUnderTest.accountCreationDAO.createAccount(any(UserDetailsEntityDTO.class))).thenReturn(userDetailsEntityDTO1);

        Exception exception = assertThrows(DuplicateUserException.class, () -> {
        	accountCreationServiceImplUnderTest.createAccount(userDetails);
        });
    }

    @Test
    public void testAddAccount() {
        AddAccountDTO addAccountDTO = new AddAccountDTO();
        addAccountDTO.setAccountType("credit");
        addAccountDTO.setCreditAmount(100);
        addAccountDTO.setUserName("username");

        when(accountCreationServiceImplUnderTest.accountCreationDAO.findByUserName(any())).thenReturn(userDetailsEntityDTO);

        when(accountCreationServiceImplUnderTest.accountCreationDAO.createAccount(any(UserDetailsEntityDTO.class))).thenReturn(userDetailsEntityDTO);

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

    @Test
    public void testAddAccount_ThrowsUserDoesNotExistsException() {
       
        final AddAccountDTO addAccountDTO = new AddAccountDTO();

        final UserDetailsEntityDTO userDetailsEntityDTO = new UserDetailsEntityDTO();
        when(accountCreationServiceImplUnderTest.accountCreationDAO.findByUserName("username")).thenReturn(userDetailsEntityDTO);

        final UserDetailsEntityDTO userDetailsEntityDTO1 = new UserDetailsEntityDTO();
        when(accountCreationServiceImplUnderTest.accountCreationDAO.createAccount(any(UserDetailsEntityDTO.class))).thenReturn(userDetailsEntityDTO1);

        Exception exception = assertThrows(UserDoesNotExistsException.class, () -> {
        	accountCreationServiceImplUnderTest.addAccount(addAccountDTO);
        });
    }
}
