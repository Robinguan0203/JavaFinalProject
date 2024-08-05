/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.fwrp.dataaccess.dto;

import com.fwrp.dbService.FoodDbService;
import com.fwrp.models.ExpireInfo;
import com.fwrp.models.Food;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.Date;

/**
 * Unit tests for the {@link ExpireInfoDTO} class.
 * This class tests the getter and setter methods of the {@link ExpireInfoDTO} class,
 * as well as the {@link ExpireInfoDTO#transferToExpireInfo()} method.
 * 
 * @version 1.0
 * Since: 17.0.8
 */
public class ExpireInfoDTOTest {

    private ExpireInfoDTO expireInfoDTO;
    private FoodDbService foodDbService;

    /**
     * Sets up the test environment before each test.
     * 
     * @throws Exception if an error occurs during setup
     */
    @Before
    public void setUp() throws Exception {
        expireInfoDTO = new ExpireInfoDTO();
        foodDbService = new FoodDbService();
    }

    /**
     * Tests the {@link ExpireInfoDTO#getId()} method.
     */
    @Test
    public void testGetId() {
        int expResult = 1;
        expireInfoDTO.setId(expResult);
        int result = expireInfoDTO.getId();
        assertEquals(expResult, result);
    }

    /**
     * Tests the {@link ExpireInfoDTO#setId(int)} method.
     */
    @Test
    public void testSetId() {
        int id = 2;
        expireInfoDTO.setId(id);
        assertEquals(id, expireInfoDTO.getId());
    }

    /**
     * Tests the {@link ExpireInfoDTO#getFoodId()} method.
     */
    @Test
    public void testGetFoodId() {
        int expResult = 3;
        expireInfoDTO.setFoodId(expResult);
        int result = expireInfoDTO.getFoodId();
        assertEquals(expResult, result);
    }

    /**
     * Tests the {@link ExpireInfoDTO#setFoodId(int)} method.
     */
    @Test
    public void testSetFoodId() {
        int foodId = 4;
        expireInfoDTO.setFoodId(foodId);
        assertEquals(foodId, expireInfoDTO.getFoodId());
    }

    /**
     * Tests the {@link ExpireInfoDTO#getQuantity()} method.
     */
    @Test
    public void testGetQuantity() {
        int expResult = 5;
        expireInfoDTO.setQuantity(expResult);
        int result = expireInfoDTO.getQuantity();
        assertEquals(expResult, result);
    }

    /**
     * Tests the {@link ExpireInfoDTO#setQuantity(int)} method.
     */
    @Test
    public void testSetQuantity() {
        int quantity = 6;
        expireInfoDTO.setQuantity(quantity);
        assertEquals(quantity, expireInfoDTO.getQuantity());
    }

    /**
     * Tests the {@link ExpireInfoDTO#getExpireDate()} method.
     */
    @Test
    public void testGetExpireDate() {
        Date expResult = new Date();
        expireInfoDTO.setExpireDate(expResult);
        Date result = expireInfoDTO.getExpireDate();
        assertEquals(expResult, result);
    }

    /**
     * Tests the {@link ExpireInfoDTO#setExpireDate(Date)} method.
     */
    @Test
    public void testSetExpireDate() {
        Date expireDate = new Date();
        expireInfoDTO.setExpireDate(expireDate);
        assertEquals(expireDate, expireInfoDTO.getExpireDate());
    }

    /**
     * Tests the {@link ExpireInfoDTO#isIsSurplus()} method.
     */
    @Test
    public void testIsIsSurplus() {
        boolean expResult = true;
        expireInfoDTO.setIsSurplus(expResult);
        boolean result = expireInfoDTO.isIsSurplus();
        assertEquals(expResult, result);
    }

    /**
     * Tests the {@link ExpireInfoDTO#setIsSurplus(boolean)} method.
     */
    @Test
    public void testSetIsSurplus() {
        boolean isSurplus = false;
        expireInfoDTO.setIsSurplus(isSurplus);
        assertEquals(isSurplus, expireInfoDTO.isIsSurplus());
    }
    
    /**
     * Tests the {@link ExpireInfoDTO#transferToExpireInfo()} method.
     * 
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the database driver class cannot be found
     */  
    @Test
    public void testTransferToExpireInfo() throws SQLException, ClassNotFoundException {
        Food testFood = foodDbService.getFoodById(2);

        expireInfoDTO.setId(1);
        expireInfoDTO.setFoodId(2);
        expireInfoDTO.setQuantity(10);
        expireInfoDTO.setExpireDate(new Date());
        expireInfoDTO.setIsSurplus(true);

        ExpireInfo result = expireInfoDTO.transferToExpireInfo();

        assertEquals(expireInfoDTO.getId(), result.getId());
        assertEquals(expireInfoDTO.getExpireDate(), result.getExpireDate());
        assertEquals(expireInfoDTO.getQuantity(), result.getQuantity());
        assertEquals(expireInfoDTO.isIsSurplus(), result.isIsSurplus());

        
        //assertEquals(testFood.getId(), result.getFood().getId());
        //assertEquals(testFood.getName(), result.getFood().getName());
        //assertEquals(testFood.getExpireDays(), result.getFood().getExpireDays());
        
    }
}
