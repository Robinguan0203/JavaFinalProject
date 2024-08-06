package com.fwrp.dbService;

import com.fwrp.dbService.userfactory.UserFactory;
import com.fwrp.constants.UserTypeConstant;
import com.fwrp.models.User;
import com.fwrp.models.Retailer;
import com.fwrp.models.Consumer;
import com.fwrp.models.Charity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the {@link UserFactory} class.
 * Tests the createUser method to ensure proper functionality for different user types.
 * 
 * Author: YAOZHOU XIE
 */
public class UserFactoryTest {

    /**
     * Test of createUser method for creating a Retailer.
     * Ensures that the created user is an instance of Retailer.
     */
    @Test
    public void testCreateRetailer() {
        User user = UserFactory.createUser(UserTypeConstant.RETAILER);
        assertTrue(user instanceof Retailer);
    }

    /**
     * Test of createUser method for creating a Consumer.
     * Ensures that the created user is an instance of Consumer.
     */
    @Test
    public void testCreateConsumer() {
        User user = UserFactory.createUser(UserTypeConstant.CONSUMER);
        assertTrue(user instanceof Consumer);
    }

    /**
     * Test of createUser method for creating a Charity.
     * Ensures that the created user is an instance of Charity.
     */
    @Test
    public void testCreateCharity() {
        User user = UserFactory.createUser(UserTypeConstant.CHARITY);
        assertTrue(user instanceof Charity);
    }

    /**
     * Test of createUser method with an invalid user type.
     * Ensures that an IllegalArgumentException is thrown for an invalid user type.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateInvalidUserType() {
        UserFactory.createUser(-1);
    }
}
