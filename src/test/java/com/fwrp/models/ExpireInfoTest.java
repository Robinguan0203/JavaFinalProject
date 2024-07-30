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

public class ExpireInfoTest {

    private ExpireInfo expireInfo;

    @Before
    public void setUp() {
        expireInfo = new ExpireInfo();
    }

    @Test
    public void testGetId() {
        expireInfo.setId(1);
        assertEquals(1, expireInfo.getId());
    }

    @Test
    public void testGetExpireDate() {
        Date date = new Date();
        expireInfo.setExpireDate(date);
        assertEquals(date, expireInfo.getExpireDate());
    }

    @Test
    public void testGetQuantity() {
        expireInfo.setQuantity(10);
        assertEquals(10, expireInfo.getQuantity());
    }

    @Test
    public void testIsIsSurplus() {
        expireInfo.setIsSurplus(true);
        assertTrue(expireInfo.isIsSurplus());
    }

    @Test
    public void testGetFood() {
        Food food = new Food(); // Assume Food class has a default constructor
        expireInfo.setFood(food);
        assertEquals(food, expireInfo.getFood());
    }

    @Test
    public void testSetId() {
        expireInfo.setId(2);
        assertEquals(2, expireInfo.getId());
    }

    @Test
    public void testSetExpireDate() {
        Date date = new Date();
        expireInfo.setExpireDate(date);
        assertEquals(date, expireInfo.getExpireDate());
    }

    @Test
    public void testSetQuantity() {
        expireInfo.setQuantity(20);
        assertEquals(20, expireInfo.getQuantity());
    }

    @Test
    public void testSetIsSurplus() {
        expireInfo.setIsSurplus(false);
        assertFalse(expireInfo.isIsSurplus());
    }

    @Test
    public void testSetFood() {
        Food food = new Food(); // Assume Food class has a default constructor
        expireInfo.setFood(food);
        assertEquals(food, expireInfo.getFood());
    }

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
