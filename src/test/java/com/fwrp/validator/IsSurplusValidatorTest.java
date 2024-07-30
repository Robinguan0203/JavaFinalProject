/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.fwrp.validator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IsSurplusValidatorTest {

    private IsSurplusValidator validator;

    @Before
    public void setUp() {
        validator = new IsSurplusValidator();
    }

    @Test
    public void testValidateValidInput() {
        assertTrue("Input '0' should be valid.", validator.validate("0"));
        assertTrue("Input '1' should be valid.", validator.validate("1"));
        assertNull("There should be no error message for valid inputs.", validator.getErrorMessage());
    }

    @Test
    public void testValidateNullInput() {
        assertFalse("Null input should fail validation.", validator.validate(null));
        assertEquals("Input cannot be empty.", validator.getErrorMessage());
    }

    @Test
    public void testValidateEmptyInput() {
        assertFalse("Empty input should fail validation.", validator.validate(""));
        assertEquals("Input cannot be empty.", validator.getErrorMessage());
    }

    @Test
    public void testValidateInvalidInput() {
        assertFalse("Input '2' should fail validation.", validator.validate("2"));
        assertEquals("Input must be true or false.", validator.getErrorMessage());

        assertFalse("Input 'true' should fail validation.", validator.validate("true"));
        assertEquals("Input must be true or false.", validator.getErrorMessage());

        assertFalse("Input 'false' should fail validation.", validator.validate("false"));
        assertEquals("Input must be true or false.", validator.getErrorMessage());
    }
}
