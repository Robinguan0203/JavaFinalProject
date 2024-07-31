/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.fwrp.validator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FoodExpireDaysValidatorTest {

    private FoodExpireDaysValidator validator;

    @Before
    public void setUp() {
        validator = new FoodExpireDaysValidator();
    }

    @Test
    public void testValidateValidInputs() {
        assertTrue("Valid food ID and expiration days should pass validation.", validator.validate(1, 10));
        assertNull("There should be no error message for valid inputs.", validator.getErrorMessage());
    }

    @Test
    public void testValidateInvalidFoodId() {
        assertFalse("A non-positive food ID should fail validation.", validator.validate(0, 10));
        assertEquals("Food ID must be positive.", validator.getErrorMessage());
        
        assertFalse("A negative food ID should fail validation.", validator.validate(-1, 10));
        assertEquals("Food ID must be positive.", validator.getErrorMessage());
    }

    @Test
    public void testValidateInvalidExpireDays() {
        assertFalse("Expiration days less than 1 should fail validation.", validator.validate(1, 0));
        assertEquals("Expire days must be greater than or equal to 1.", validator.getErrorMessage());
        
        assertFalse("A negative expiration days should fail validation.", validator.validate(1, -5));
        assertEquals("Expire days must be greater than or equal to 1.", validator.getErrorMessage());
    }

    @Test
    public void testValidateInvalidFoodIdAndExpireDays() {
        assertFalse("Both invalid food ID and expiration days should fail validation.", validator.validate(-1, 0));
        assertEquals("Food ID must be positive.", validator.getErrorMessage());
    }
}