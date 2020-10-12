package com.banking.account.config;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class AccountConfigTest {

    private AccountConfig accountConfigUnderTest;

    @Before
    public void setUp() {
        accountConfigUnderTest = new AccountConfig();
    }

    @Test
    public void testModelMapper() {

        final ModelMapper result = accountConfigUnderTest.modelMapper();

        Assert.assertNotNull(result);
    }
}
