package com.fwrp.dbService;

import com.fwrp.constants.UserTypeConstant;
import com.fwrp.models.User;
import com.fwrp.models.Retailer;
import com.fwrp.models.Consumer;
import com.fwrp.models.Charity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the UserFactory class.
 * @author YAOZHOU XIE
 */
public class UserFactoryTest {

    @Test
    public void testCreateRetailer() {
        User user = UserFactory.createUser(UserTypeConstant.RETAILER);
        assertTrue(user instanceof Retailer);
    }

    @Test
    public void testCreateConsumer() {
        User user = UserFactory.createUser(UserTypeConstant.CONSUMER);
        assertTrue(user instanceof Consumer);
    }

    @Test
    public void testCreateCharity() {
        User user = UserFactory.createUser(UserTypeConstant.CHARITY);
        assertTrue(user instanceof Charity);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateInvalidUserType() {
        UserFactory.createUser(-1);
    }
}
