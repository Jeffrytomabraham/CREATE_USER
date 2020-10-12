package com.banking.account.component;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.banking.account.util.ValidationMessages;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.banking.account.exception.DuplicateUserException;
import com.banking.account.exception.UserDoesNotExistsException;
import com.banking.account.request.dto.AddAccountDTO;
import com.banking.account.request.dto.UserDetailsDTO;
import com.banking.account.response.dto.AccountDetailsDTO;
import com.banking.account.response.dto.AccountsDTO;
import com.banking.account.service.AccountCreationService;

@RunWith(MockitoJUnitRunner.class)
public class AccountCreationComponentTest {

    private AccountCreationComponent accountCreationComponentUnderTest;
    AccountDetailsDTO accountDetailsDTO = new AccountDetailsDTO();

    @Before
    public void setUp() {
        accountCreationComponentUnderTest = new AccountCreationComponent();
        accountCreationComponentUnderTest.accountCreationService = mock(AccountCreationService.class);
        accountDetailsDTO.setAddress1("temp1");
        accountDetailsDTO.setAddress2("temp1");
        accountDetailsDTO.setAge(10);
        accountDetailsDTO.setCountry("India");
        accountDetailsDTO.setCity("tvm");
        accountDetailsDTO.setDob("02-02-2020");
        accountDetailsDTO.setEmail("abc@abc.com");
        accountDetailsDTO.setUserName("username");
        accountDetailsDTO.setPostalCode("698754");
        accountDetailsDTO.setFirstName("firstname");
        accountDetailsDTO.setLastName("lastname");
        accountDetailsDTO.setPhone("123654789");
        AccountsDTO account = new AccountsDTO();
        account.setAccountNumber("123");
        account.setAccountType("SAVINGS");
        account.setBalance(1000);
        List<AccountsDTO> accountList = new ArrayList<>();
        accountList.add(account);
        accountDetailsDTO.setAccounts(accountList);

    }

    @Test
    public void testCreateAccount() {
        UserDetailsDTO userDetails = new UserDetailsDTO();

        when(accountCreationComponentUnderTest.accountCreationService.createAccount(any(UserDetailsDTO.class))).thenReturn(accountDetailsDTO);

        final AccountDetailsDTO result = accountCreationComponentUnderTest.createAccount(userDetails);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getUserName(), "username");
        Assert.assertNull(result.getErrorResponse());
        Assert.assertEquals(result.getFirstName(), "firstname");
        Assert.assertEquals(result.getLastName(), "lastname");
        Assert.assertEquals(result.getPhone(), "123654789");
        Assert.assertEquals(result.getPostalCode(), "698754");
        Assert.assertTrue(result.getAccounts().size()>0);
        Assert.assertEquals(result.getAddress1(), "temp1");
        Assert.assertEquals(result.getAddress2(), "temp1");
        Assert.assertTrue(result.getAge()==10);
        Assert.assertEquals(result.getCity(), "tvm");
        Assert.assertEquals(result.getCountry(), "India");
        Assert.assertEquals(result.getDob(), "02-02-2020");
        Assert.assertEquals(result.getEmail(), "abc@abc.com");
        
    }

    @Test
    public void testCreateAccount_AccountCreationServiceThrowsDuplicateUserException() {

        UserDetailsDTO userDetails =  new UserDetailsDTO();

        when(accountCreationComponentUnderTest.accountCreationService.createAccount(any(UserDetailsDTO.class))).thenThrow(DuplicateUserException.class);

        final AccountDetailsDTO result = accountCreationComponentUnderTest.createAccount(userDetails);

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getErrorResponse());
        Assert.assertEquals(result.getErrorResponse().getErrorCode(), ValidationMessages.DUPLICATE_USER.getCode());
    }

    @Test
    public void testAddAccount() {
        final AddAccountDTO addAccountDTO = new AddAccountDTO();

        when(accountCreationComponentUnderTest.accountCreationService.addAccount(any(AddAccountDTO.class))).thenReturn(accountDetailsDTO);

        final AccountDetailsDTO result = accountCreationComponentUnderTest.addAccount(addAccountDTO);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getUserName(), "username");
        Assert.assertNull(result.getErrorResponse());
        Assert.assertEquals(result.getFirstName(), "firstname");
        Assert.assertEquals(result.getLastName(), "lastname");
        Assert.assertEquals(result.getPhone(), "123654789");
        Assert.assertEquals(result.getPostalCode(), "698754");
        Assert.assertTrue(result.getAccounts().size()>0);
        Assert.assertEquals(result.getAddress1(), "temp1");
        Assert.assertEquals(result.getAddress2(), "temp1");
        Assert.assertTrue(result.getAge()==10);
        Assert.assertEquals(result.getCity(), "tvm");
        Assert.assertEquals(result.getCountry(), "India");
        Assert.assertEquals(result.getDob(), "02-02-2020");
        Assert.assertEquals(result.getEmail(), "abc@abc.com");
    }

    @Test
    public void testAddAccount_AccountCreationServiceThrowsUserDoesNotExistsException() {

        final AddAccountDTO addAccountDTO = new AddAccountDTO();

        when(accountCreationComponentUnderTest.accountCreationService.addAccount(any(AddAccountDTO.class))).thenThrow(UserDoesNotExistsException.class);

        final AccountDetailsDTO result = accountCreationComponentUnderTest.addAccount(addAccountDTO);

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getErrorResponse());
        Assert.assertEquals(result.getErrorResponse().getErrorCode(), ValidationMessages.INVALID_USER.getCode());
    }
}
