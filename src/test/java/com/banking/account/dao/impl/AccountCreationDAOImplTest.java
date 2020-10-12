package com.banking.account.dao.impl;

import com.banking.account.Entity.UserDetailsEntityDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountCreationDAOImplTest {

    private AccountCreationDAOImpl accountCreationDAOImplUnderTest;

    @Before
    public void setUp() {
        accountCreationDAOImplUnderTest = new AccountCreationDAOImpl();
        accountCreationDAOImplUnderTest.mongoTemplate = mock(MongoTemplate.class);
    }

    @Test
    public void testCreateAccount() {
        final UserDetailsEntityDTO userDetails = new UserDetailsEntityDTO();

        final UserDetailsEntityDTO userDetailsEntityDTO = new UserDetailsEntityDTO();
        when(accountCreationDAOImplUnderTest.mongoTemplate.save(any(UserDetailsEntityDTO.class))).thenReturn(userDetailsEntityDTO);

        final UserDetailsEntityDTO result = accountCreationDAOImplUnderTest.createAccount(userDetails);

        Assert.assertNotNull(result);
    }

    //@Test
    public void testFindByUserName() {
        final UserDetailsEntityDTO userDetailsEntityDTO = new UserDetailsEntityDTO();
        final List<UserDetailsEntityDTO> userDetailsEntityDTOS = Arrays.asList(userDetailsEntityDTO);
        when(accountCreationDAOImplUnderTest.mongoTemplate.find(new Query(null), UserDetailsEntityDTO.class)).thenReturn(userDetailsEntityDTOS);

        final UserDetailsEntityDTO result = accountCreationDAOImplUnderTest.findByUserName("username");
        Assert.assertNotNull(result);
    }
}
