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
 * Unit tests for the Inventory class.
 * Tests the getter and setter methods to ensure proper functionality.
 * 
 * @author robin
 */
public class InventoryTest {

    private Inventory inventory;
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
        // Initialize a Food object for use in the tests
        food = new Food("Apple", 10, 2.5, 0.1);
        inventory = new Inventory(1, food, 100, 50, 25);
    }

    /**
     * Cleans up the test environment after each test.
     */
    @After
    public void tearDown() {
        inventory = null;
        food = null;
    }

    /**
     * Test of getId method, of class Inventory.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        int expResult = 1;
        int result = inventory.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Inventory.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 2;
        inventory.setId(id);
        assertEquals(id, inventory.getId());
    }

    /**
     * Test of getFood method, of class Inventory.
     */
    @Test
    public void testGetFood() {
        System.out.println("getFood");
        Food expResult = food;
        Food result = inventory.getFood();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFood method, of class Inventory.
     */
    @Test
    public void testSetFood() {
        System.out.println("setFood");
        Food newFood = new Food("Banana", 12, 3.0, 0.2);
        inventory.setFood(newFood);
        assertEquals(newFood, inventory.getFood());
    }

    /**
     * Test of getQtyNormal method, of class Inventory.
     */
    @Test
    public void testGetQtyNormal() {
        System.out.println("getQtyNormal");
        int expResult = 100;
        int result = inventory.getQtyNormal();
        assertEquals(expResult, result);
    }

    /**
     * Test of setQtyNormal method, of class Inventory.
     */
    @Test
    public void testSetQtyNormal() {
        System.out.println("setQtyNormal");
        int qtyNormal = 120;
        inventory.setQtyNormal(qtyNormal);
        assertEquals(qtyNormal, inventory.getQtyNormal());
    }

    /**
     * Test of getQtyDiscount method, of class Inventory.
     */
    @Test
    public void testGetQtyDiscount() {
        System.out.println("getQtyDiscount");
        int expResult = 50;
        int result = inventory.getQtyDiscount();
        assertEquals(expResult, result);
    }

    /**
     * Test of setQtyDiscount method, of class Inventory.
     */
    @Test
    public void testSetQtyDiscount() {
        System.out.println("setQtyDiscount");
        int qtyDiscount = 60;
        inventory.setQtyDiscount(qtyDiscount);
        assertEquals(qtyDiscount, inventory.getQtyDiscount());
    }

    /**
     * Test of getQtyDonation method, of class Inventory.
     */
    @Test
    public void testGetQtyDonation() {
        System.out.println("getQtyDonation");
        int expResult = 25;
        int result = inventory.getQtyDonation();
        assertEquals(expResult, result);
    }

    /**
     * Test of setQtyDonation method, of class Inventory.
     */
    @Test
    public void testSetQtyDonation() {
        System.out.println("setQtyDonation");
        int qtyDonation = 30;
        inventory.setQtyDonation(qtyDonation);
        assertEquals(qtyDonation, inventory.getQtyDonation());
    }
}
