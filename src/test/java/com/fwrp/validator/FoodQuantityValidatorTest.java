/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.fwrp.validator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the {@link FoodQuantityValidator} class.
 * Tests the validate method to ensure proper functionality.
 */
public class FoodQuantityValidatorTest {

    private FoodQuantityValidator validator;

    /**
     * Sets up the test environment before each test.
     */
    @Before
    public void setUp() {
        validator = new FoodQuantityValidator();
    }

    /**
     * Test of validate method with valid inputs.
     */
    @Test
    public void testValidateValidInputs() {
        assertTrue("Valid food ID and quantity should pass validation.", validator.validate(1, 10));
        assertNull("There should be no error message for valid inputs.", validator.getErrorMessage());
    }

    /**
     * Test of validate method with an invalid food ID.
     */
    @Test
    public void testValidateInvalidFoodId() {
        assertFalse("A non-positive food ID should fail validation.", validator.validate(0, 10));
        assertEquals("Food ID must be positive.", validator.getErrorMessage());
        
        assertFalse("A negative food ID should fail validation.", validator.validate(-1, 10));
        assertEquals("Food ID must be positive.", validator.getErrorMessage());
    }

    /**
     * Test of validate method with an invalid quantity.
     */
    @Test
    public void testValidateInvalidQuantity() {
        assertFalse("A zero quantity should fail validation.", validator.validate(1, 0));
        assertEquals("Quantity cannot be negative or zero.", validator.getErrorMessage());
        
        assertFalse("A negative quantity should fail validation.", validator.validate(1, -5));
        assertEquals("Quantity cannot be negative or zero.", validator.getErrorMessage());
    }

    /**
     * Test of validate method with both invalid food ID and quantity.
     */
    @Test
    public void testValidateInvalidFoodIdAndQuantity() {
        assertFalse("Both invalid food ID and quantity should fail validation.", validator.validate(-1, 0));
        assertEquals("Food ID must be positive.", validator.getErrorMessage());
    }
}
