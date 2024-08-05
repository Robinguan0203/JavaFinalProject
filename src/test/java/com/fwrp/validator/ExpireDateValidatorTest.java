/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.fwrp.validator;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Unit tests for the {@link ExpireDateValidator} class.
 * Tests the validate method to ensure proper functionality.
 */
public class ExpireDateValidatorTest {

    private ExpireDateValidator validator;

    /**
     * Sets up the test environment before each test.
     */
    @Before
    public void setUp() {
        validator = new ExpireDateValidator();
    }

    /**
     * Test of validate method with a future date.
     */
    @Test
    public void testValidateFutureDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1); // Tomorrow
        Date futureDate = calendar.getTime();

        assertTrue("The future date should be valid.", validator.validate(futureDate));
        assertNull("There should be no error message.", validator.getErrorMessage());
    }

     /**
     * Test of validate method with today's date.
     */
    @Test
    public void testValidateTodayDate() {
        Date todayDate = new Date(); // Current date

        assertTrue("Today's date should be valid.", validator.validate(todayDate));
        assertNull("There should be no error message.", validator.getErrorMessage());
    }

    /**
     * Test of validate method with a past date.
     */
    @Test
    public void testValidatePastDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1); // Yesterday
        Date pastDate = calendar.getTime();

        assertFalse("The past date should be invalid.", validator.validate(pastDate));
        assertEquals("Expire date must be later than today.", validator.getErrorMessage());
    }

    /**
     * Test of validate method with a null date.
     */
    @Test
    public void testValidateNullDate() {
        assertTrue("A null date should be considered valid.", validator.validate(null));
        assertNull("There should be no error message.", validator.getErrorMessage());
    }
}
