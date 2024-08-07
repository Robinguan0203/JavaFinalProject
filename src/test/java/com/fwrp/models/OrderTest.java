package com.fwrp.models;

import org.junit.*;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the Order class.
 * Tests the getter and setter methods to ensure proper functionality.
 * 
 * @version 2.0
 * @author Ke Yan
 */
public class OrderTest {

    private Order order;

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
        order = new Order();
    }

    /**
     * Cleans up the test environment after each test.
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Order.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        int expResult = 0;
        order.setId(expResult);
        int result = order.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Order.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 1;
        order.setId(id);
        assertEquals(id, order.getId());
    }

    /**
     * Test of getQtyDiscount method, of class Order.
     */
    @Test
    public void testGetQtyDiscount() {
        System.out.println("getQtyDiscount");
        int expResult = 1;
        order.setQtyDiscount(1);
        int result = order.getQtyDiscount();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDate method, of class Order.
     */
    @Test
    public void testSetDate() {
        Date date = new Date();
        order.setDate(date);
        assertEquals(date, order.getDate());
    }
}
