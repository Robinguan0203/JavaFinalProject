/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.fwrp.validator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the {@link UserValidator} class.
 * Tests the validate method to ensure proper functionality.
 */
public class UserValidatorTest {

    private UserValidator validator;

    /**
     * Sets up the test environment before each test.
     */
    @Before
    public void setUp() {
        validator = new UserValidator();
    }

    /**
     * Test of validate method with valid details.
     */
    @Test
    public void testValidateValidDetails() {
        assertTrue("Valid details should be accepted.", 
            validator.validate("John", "Doe", "1234567890", "john.doe@example.com", "password123", "password123", "1", "Organization"));
        assertNull("There should be no error message for valid inputs.", validator.getErrorMessage());
    }

    /**
     * Test of validate method with an empty first name.
     */
    @Test
    public void testValidateEmptyFirstName() {
        assertFalse("Empty first name should be invalid.", 
            validator.validate("", "Doe", "1234567890", "john.doe@example.com", "password123", "password123", "1", "Organization"));
        assertEquals("First name cannot be empty.", validator.getErrorMessage());
    }

    /**
     * Test of validate method with an empty last name.
     */
    @Test
    public void testValidateEmptyLastName() {
        assertFalse("Empty last name should be invalid.", 
            validator.validate("John", "", "1234567890", "john.doe@example.com", "password123", "password123", "1", "Organization"));
        assertEquals("Last name cannot be empty.", validator.getErrorMessage());
    }

    /**
     * Test of validate method with an invalid phone number.
     */
    @Test
    public void testValidateInvalidPhone() {
        assertFalse("Invalid phone number should be invalid.", 
            validator.validate("John", "Doe", "123abc7890", "john.doe@example.com", "password123", "password123", "1", "Organization"));
        assertEquals("Phone number must be numeric.", validator.getErrorMessage());
    }

    /**
     * Test of validate method with an empty email.
     */
    @Test
    public void testValidateEmptyEmail() {
        assertFalse("Empty email should be invalid.", 
            validator.validate("John", "Doe", "1234567890", "", "password123", "password123", "1", "Organization"));
        assertEquals("Email cannot be empty.", validator.getErrorMessage());
    }

    /**
     * Test of validate method with an empty password.
     */
    @Test
    public void testValidateEmptyPassword() {
        assertFalse("Empty password should be invalid.", 
            validator.validate("John", "Doe", "1234567890", "john.doe@example.com", "", "password123", "1", "Organization"));
        assertEquals("Password cannot be empty.", validator.getErrorMessage());
    }

    /**
     * Test of validate method with mismatched passwords.
     */
    @Test
    public void testValidatePasswordMismatch() {
        assertFalse("Mismatched passwords should be invalid.", 
            validator.validate("John", "Doe", "1234567890", "john.doe@example.com", "password123", "password321", "1", "Organization"));
        assertEquals("Passwords do not match.", validator.getErrorMessage());
    }

    /**
     * Test of validate method with an invalid user type.
     */
    @Test
    public void testValidateInvalidUserType() {
        assertFalse("Invalid user type should be invalid.", 
            validator.validate("John", "Doe", "1234567890", "john.doe@example.com", "password123", "password123", "5", "Organization"));
        assertEquals("Invalid user type.", validator.getErrorMessage());

        assertFalse("Non-numeric user type should be invalid.", 
            validator.validate("John", "Doe", "1234567890", "john.doe@example.com", "password123", "password123", "abc", "Organization"));
        assertEquals("User type must be a number.", validator.getErrorMessage());
    }

    /**
     * Test of validate method with an empty organization for a Retailer.
     */
    @Test
    public void testValidateEmptyOrganizationForRetailer() {
        assertFalse("Empty organization for Retailer should be invalid.", 
            validator.validate("John", "Doe", "1234567890", "john.doe@example.com", "password123", "password123", "1", ""));
        assertEquals("Organization name cannot be empty for Retailer or Charity.", validator.getErrorMessage());
    }

    /**
     * Test of validate method with an empty organization for a Charity.
     */
    @Test
    public void testValidateEmptyOrganizationForCharity() {
        assertFalse("Empty organization for Charity should be invalid.", 
            validator.validate("John", "Doe", "1234567890", "john.doe@example.com", "password123", "password123", "2", ""));
        assertEquals("Organization name cannot be empty for Retailer or Charity.", validator.getErrorMessage());
    }

    /**
     * Test of validate method with a valid organization for a Consumer.
     */
    @Test
    public void testValidateValidOrganizationForConsumer() {
        assertTrue("Non-empty organization for Consumer should be valid.", 
            validator.validate("John", "Doe", "1234567890", "john.doe@example.com", "password123", "password123", "3", "Consumer Organization"));
        assertNull("There should be no error message for valid inputs.", validator.getErrorMessage());
    }
}
