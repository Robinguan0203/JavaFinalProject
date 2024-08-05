/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.fwrp.models;

import com.fwrp.dataaccess.dto.ExpireInfoDTO;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Unit tests for the {@link ExpireInfo} class.
 * Tests the getter and setter methods to ensure proper functionality.
 * Also tests the transfer to ExpireInfoDTO method.
 * 
 */
public class ExpireInfoTest {

    private ExpireInfo expireInfo;

    /**
     * Sets up the test environment before each test.
     */
    @Before
    public void setUp() {
        expireInfo = new ExpireInfo();
    }

    /**
     * Test of getId method, of class ExpireInfo.
     */
    @Test
    public void testGetId() {
        expireInfo.setId(1);
        assertEquals(1, expireInfo.getId());
    }

    /**
     * Test of getExpireDate method, of class ExpireInfo.
     */
    @Test
    public void testGetExpireDate() {
        Date date = new Date();
        expireInfo.setExpireDate(date);
        assertEquals(date, expireInfo.getExpireDate());
    }

    /**
     * Test of getQuantity method, of class ExpireInfo.
     */
    @Test
    public void testGetQuantity() {
        expireInfo.setQuantity(10);
        assertEquals(10, expireInfo.getQuantity());
    }

    /**
     * Test of isIsSurplus method, of class ExpireInfo.
     */
    @Test
    public void testIsIsSurplus() {
        expireInfo.setIsSurplus(true);
        assertTrue(expireInfo.isIsSurplus());
    }

    /**
     * Test of getFood method, of class ExpireInfo.
     */
    @Test
    public void testGetFood() {
        Food food = new Food(); // Assume Food class has a default constructor
        expireInfo.setFood(food);
        assertEquals(food, expireInfo.getFood());
    }

    /**
     * Test of setId method, of class ExpireInfo.
     */
    @Test
    public void testSetId() {
        expireInfo.setId(2);
        assertEquals(2, expireInfo.getId());
    }
    
    /**
     * Test of setExpireDate method, of class ExpireInfo.
     */
    @Test
    public void testSetExpireDate() {
        Date date = new Date();
        expireInfo.setExpireDate(date);
        assertEquals(date, expireInfo.getExpireDate());
    }

    /**
     * Test of setQuantity method, of class ExpireInfo.
     */
    @Test
    public void testSetQuantity() {
        expireInfo.setQuantity(20);
        assertEquals(20, expireInfo.getQuantity());
    }

    /**
     * Test of setIsSurplus method, of class ExpireInfo.
     */
    @Test
    public void testSetIsSurplus() {
        expireInfo.setIsSurplus(false);
        assertFalse(expireInfo.isIsSurplus());
    }

    /**
     * Test of setFood method, of class ExpireInfo.
     */
    @Test
    public void testSetFood() {
        Food food = new Food(); // Assume Food class has a default constructor
        expireInfo.setFood(food);
        assertEquals(food, expireInfo.getFood());
    }

    /**
    * Test of transferToExpireInfoDTO method, of class ExpireInfo.
    * This test ensures that the transferToExpireInfoDTO method correctly converts an ExpireInfo object to an ExpireInfoDTO object.
    *
    * @throws SQLException if a database access error occurs
    * @throws ClassNotFoundException if the Food class is not found
    */
    @Test
    public void testTransferToExpireInfoDTO() throws SQLException, ClassNotFoundException {
        expireInfo.setId(1);
        expireInfo.setExpireDate(new Date());
        expireInfo.setQuantity(15);
        expireInfo.setIsSurplus(true);
        Food food = new Food(); // Assume Food class has a default constructor
        expireInfo.setFood(food);
        
        ExpireInfoDTO dto = expireInfo.transferToExpireInfoDTO();
        
        assertNotNull(dto);
        assertEquals(expireInfo.getId(), dto.getId());
        assertEquals(expireInfo.getExpireDate(), dto.getExpireDate());
        assertEquals(expireInfo.getQuantity(), dto.getQuantity());
        assertEquals(expireInfo.isIsSurplus(), dto.isIsSurplus());
    }
}
