package com.banking.account.request.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsDTOTest {

    private UserDetailsDTO userDetailsDTOUnderTest;

    @BeforeEach
    public void setUp() {
        userDetailsDTOUnderTest = new UserDetailsDTO();
    }

    @Test
    public void setUserDetails(){
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
        
        assertEquals(userDetailsDTO.getAddress1(),"temp1");
        assertEquals(userDetailsDTO.getAddress2(),"temp1");
        assertEquals(userDetailsDTO.getAge(),10);
        assertEquals(userDetailsDTO.getCountry(),"India");
        assertEquals(userDetailsDTO.getCity(),"tvm");
        assertEquals(userDetailsDTO.getDob(),"02-02-2020");
        assertEquals(userDetailsDTO.getEmail(),"abc@abc.com");
        assertEquals(userDetailsDTO.getUserName(),"username");
        assertEquals(userDetailsDTO.getPostalCode(),"698754");
        assertEquals(userDetailsDTO.getFirstName(),"firstname");
        assertEquals(userDetailsDTO.getLastName(),"lastname");
        assertTrue(userDetailsDTO.getCreditAmount()==100);
        assertEquals(userDetailsDTO.getPassword(),"pass");
        assertEquals(userDetailsDTO.getAccountType(),"savings");
        assertEquals(userDetailsDTO.getPhone(),"123654789");
    }
}
