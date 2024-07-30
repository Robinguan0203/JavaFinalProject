/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.fwrp.validator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FoodValidatorTest {

    private FoodValidator validator;

    @Before
    public void setUp() {
        validator = new FoodValidator();
    }

    @Test
    public void testValidateValidData() {
        assertTrue("Valid data should pass validation.", validator.validate("Apple", 10, 2.5, 0.1));
        assertNull("There should be no error message for valid inputs.", validator.getErrorMessage());
    }

    @Test
    public void testValidateInvalidName() {
        assertFalse("Null name should fail validation.", validator.validate(null, 10, 2.5, 0.1));
        assertEquals("Name cannot be empty.", validator.getErrorMessage());

        assertFalse("Empty name should fail validation.", validator.validate("   ", 10, 2.5, 0.1));
        assertEquals("Name cannot be empty.", validator.getErrorMessage());
    }

    @Test
    public void testValidateInvalidExpireDays() {
        assertFalse("Zero expiration days should fail validation.", validator.validate("Apple", 0, 2.5, 0.1));
        assertEquals("Expire days cannot be negative or zero.", validator.getErrorMessage());

        assertFalse("Negative expiration days should fail validation.", validator.validate("Apple", -5, 2.5, 0.1));
        assertEquals("Expire days cannot be negative or zero.", validator.getErrorMessage());
    }

    @Test
    public void testValidateInvalidUnitPrice() {
        assertFalse("Negative unit price should fail validation.", validator.validate("Apple", 10, -2.5, 0.1));
        assertEquals("Unit price cannot be negative.", validator.getErrorMessage());
    }

    @Test
    public void testValidateInvalidDiscount() {
        assertFalse("Discount less than 0 should fail validation.", validator.validate("Apple", 10, 2.5, -0.1));
        assertEquals("Discount must be between 0 and 1.", validator.getErrorMessage());

        assertFalse("Discount greater than 1 should fail validation.", validator.validate("Apple", 10, 2.5, 1.5));
        assertEquals("Discount must be between 0 and 1.", validator.getErrorMessage());
    }
}
