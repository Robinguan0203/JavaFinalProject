/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.fwrp.models;

import org.junit.*;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the Food class.
 * Tests the getter and setter methods to ensure proper functionality.
 * 
 * @author robin
 */
public class ClaimTest {

    private Claim claim;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        claim = new Claim();
    }

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
        claim.setId(expResult);
        int result = claim.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Food.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 1;
        claim.setId(id);
        assertEquals(id, claim.getId());
    }

    /**
     * Test of getName method, of class Food.
     */
    @Test
    public void testGetQtyDonation() {
        System.out.println("getQtyDonation");
        int expResult = 1;
        claim.setQtyDonation(1);
        int result = claim.getQtyDonation();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Food.
     */
    @Test
    public void testSetDate() {
        Date date = new Date();
        claim.setDate(date);
        assertEquals(date, claim.getDate());
    }

}