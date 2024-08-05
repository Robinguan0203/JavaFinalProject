/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.fwrp.models;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Food class.
 * Tests the getter and setter methods to ensure proper functionality.
 * 
 * @author robin
 */
public class FoodTest {

    private Food food;

    /**
     * Sets up the test environment before any tests are run.
     */
    @BeforeClass
    public static void setUpClass() {
    }

    /**
     * Cleans up the test environment after all tests have been run.
     */
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Sets up the test environment before each test.
     */
    @Before
    public void setUp() {
        food = new Food();
    }

    /**
     * Cleans up the test environment after each test.
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Food.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        int expResult = 0;
        food.setId(expResult);
        int result = food.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Food.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 1;
        food.setId(id);
        assertEquals(id, food.getId());
    }

    /**
     * Test of getName method, of class Food.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "Apple";
        food.setName(expResult);
        String result = food.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Food.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "Banana";
        food.setName(name);
        assertEquals(name, food.getName());
    }

    /**
     * Test of getExpireDays method, of class Food.
     */
    @Test
    public void testGetExpireDays() {
        System.out.println("getExpireDays");
        int expResult = 10;
        food.setExpireDays(expResult);
        int result = food.getExpireDays();
        assertEquals(expResult, result);
    }

    /**
     * Test of setExpireDays method, of class Food.
     */
    @Test
    public void testSetExpireDays() {
        System.out.println("setExpireDays");
        int expireDays = 15;
        food.setExpireDays(expireDays);
        assertEquals(expireDays, food.getExpireDays());
    }

    /**
     * Test of getUnitPrice method, of class Food.
     */
    @Test
    public void testGetUnitPrice() {
        System.out.println("getUnitPrice");
        double expResult = 2.5;
        food.setUnitPrice(expResult);
        double result = food.getUnitPrice();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setUnitPrice method, of class Food.
     */
    @Test
    public void testSetUnitPrice() {
        System.out.println("setUnitPrice");
        double unitPrice = 3.0;
        food.setUnitPrice(unitPrice);
        assertEquals(unitPrice, food.getUnitPrice(), 0.0);
    }

    /**
     * Test of getDiscount method, of class Food.
     */
    @Test
    public void testGetDiscount() {
        System.out.println("getDiscount");
        double expResult = 0.1;
        food.setDiscount(expResult);
        double result = food.getDiscount();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setDiscount method, of class Food.
     */
    @Test
    public void testSetDiscount() {
        System.out.println("setDiscount");
        double discount = 0.2;
        food.setDiscount(discount);
        assertEquals(discount, food.getDiscount(), 0.0);
    }
}