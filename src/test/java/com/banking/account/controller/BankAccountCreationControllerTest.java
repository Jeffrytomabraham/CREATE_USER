package com.banking.account.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.banking.account.component.AccountCreationComponent;
import com.banking.account.request.dto.AddAccountDTO;
import com.banking.account.request.dto.UserDetailsDTO;
import com.banking.account.response.dto.AccountDetailsDTO;
import com.banking.account.response.dto.AccountsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class BankAccountCreationControllerTest {

    private BankAccountCreationController bankAccountCreationControllerUnderTest;
    AccountDetailsDTO accountDetailsDTO = new AccountDetailsDTO();
    private MockMvc mockMvc;
    @BeforeEach
    public void setUp() {
        bankAccountCreationControllerUnderTest = new BankAccountCreationController();
        bankAccountCreationControllerUnderTest.accountCreationComponent = mock(AccountCreationComponent.class);
        mockMvc= MockMvcBuilders.standaloneSetup(bankAccountCreationControllerUnderTest).build();
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
    public void testCreateUserAccount() throws Exception{
    	UserDetailsDTO userDetailsDTO= new UserDetailsDTO();
    	userDetailsDTO.setAddress1("temp1");
    	userDetailsDTO.setAddress2("temp1");
    	userDetailsDTO.setAge(10);
    	userDetailsDTO.setCountry("India");
    	userDetailsDTO.setCity("tvm");
    	userDetailsDTO.setDob("02-02-2020");
    	userDetailsDTO.setEmail("abc@abc.com");
    	userDetailsDTO.setUserName("username");
    	userDetailsDTO.setPostalCode("698754");
    	userDetailsDTO.setFirstName("firstname");
    	userDetailsDTO.setLastName("lastname");
    	userDetailsDTO.setCreditAmount(100);
    	userDetailsDTO.setPassword("pass");
    	userDetailsDTO.setAccountType("savings");
    	userDetailsDTO.setPhone("123654789");

        when(bankAccountCreationControllerUnderTest.accountCreationComponent.createAccount(any(UserDetailsDTO.class))).thenReturn(accountDetailsDTO);

        MvcResult result = mockMvc.perform( MockMvcRequestBuilders
      	      .post("/banking/user/create/account")
      	      .content(asJsonString(userDetailsDTO))
      	      .contentType(MediaType.APPLICATION_JSON)
      	      .accept(MediaType.APPLICATION_JSON)).andReturn();
	    String content = result.getResponse().getContentAsString();
	    Assert.assertEquals(content,asJsonString(accountDetailsDTO));
	  
    }

    @Test
    public void testAddUserAccount() throws Exception{

    	AddAccountDTO addAccountDTO = new AddAccountDTO();
    	addAccountDTO.setAccountType("Savings");
    	addAccountDTO.setCreditAmount(100);
    	addAccountDTO.setUserName("username");
        when(bankAccountCreationControllerUnderTest.accountCreationComponent.addAccount(any(AddAccountDTO.class))).thenReturn(accountDetailsDTO);
        
        MvcResult result = mockMvc.perform( MockMvcRequestBuilders
        	      .post("/banking/user/add/account")
        	      .content(asJsonString(addAccountDTO))
        	      .contentType(MediaType.APPLICATION_JSON)
        	      .accept(MediaType.APPLICATION_JSON)).andReturn();
  	    String content = result.getResponse().getContentAsString();
  	    Assert.assertEquals(content,asJsonString(accountDetailsDTO));
    }
    
    public  String asJsonString( Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
