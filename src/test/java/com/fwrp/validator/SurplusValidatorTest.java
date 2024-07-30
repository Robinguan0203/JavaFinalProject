/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.fwrp.validator;

import com.fwrp.models.Food;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class SurplusValidatorTest {

    private SurplusValidator validator;

    @Before
    public void setUp() {
        validator = new SurplusValidator();
    }

    @Test
    public void testValidateValidQuantities() {
        Food food = new Food(1, "Apple",30,1.50,0.7);
        HashMap<Food, Integer[]> foodExpireQtyMap = new HashMap<>();
        foodExpireQtyMap.put(food, new Integer[]{100, 0, 10, 5}); // {surplusQty, ..., qtyDiscounted, qtyDonated}

        assertTrue("Valid quantities should be accepted.", validator.validate(1, 10, 5, foodExpireQtyMap));
        assertNull("There should be no error message for valid inputs.", validator.getErrorMessage());
    }

    @Test
    public void testValidateNegativeQuantities() {
        Food food = new Food(2, "Banana",30,1.50,0.7);
        HashMap<Food, Integer[]> foodExpireQtyMap = new HashMap<>();
        foodExpireQtyMap.put(food, new Integer[]{100, 0, 10, 5}); // {surplusQty, ..., qtyDiscounted, qtyDonated}

        assertFalse("Negative quantity should be invalid.", validator.validate(2, -5, 5, foodExpireQtyMap));
        assertEquals("Invalid quantities: quantity to discount and quantity to donate must be non-negative and their sum must not exceed available surplus quantity.", validator.getErrorMessage());

        assertFalse("Negative quantity should be invalid.", validator.validate(2, 10, -5, foodExpireQtyMap));
        assertEquals("Invalid quantities: quantity to discount and quantity to donate must be non-negative and their sum must not exceed available surplus quantity.", validator.getErrorMessage());
    }

    @Test
    public void testValidateExceedingSurplus() {
        Food food = new Food(3, "Orange",30,1.50,0.7);
        HashMap<Food, Integer[]> foodExpireQtyMap = new HashMap<>();
        foodExpireQtyMap.put(food, new Integer[]{50, 0, 20, 10}); 

        assertFalse("Exceeding surplus quantity should be invalid.", validator.validate(3, 30, 20, foodExpireQtyMap));
        assertEquals("Invalid quantities: quantity to discount and quantity to donate must be non-negative and their sum must not exceed available surplus quantity.", validator.getErrorMessage());
    }

    @Test
    public void testValidateFoodIdNotFound() {
        Food food = new Food(4, "Grapes",30,1.50,0.7);
        HashMap<Food, Integer[]> foodExpireQtyMap = new HashMap<>();
        foodExpireQtyMap.put(new Food(5, "Peach",30,1.50,0.7), new Integer[]{100, 0, 10, 5}); 

        assertFalse("Food ID not found should be invalid.", validator.validate(4, 10, 5, foodExpireQtyMap));
        assertEquals("Food ID not found.", validator.getErrorMessage());
    }
}
