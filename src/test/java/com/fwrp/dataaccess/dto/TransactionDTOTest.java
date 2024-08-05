/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.fwrp.dataaccess.dto;

import com.fwrp.dataaccess.dto.TransactionDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit tests for the {@link TransactionDTO} class.
 * This class tests the constructors and getter/setter methods of the {@link TransactionDTO} class.
 * 
 * @version 1.0
 * Since: 17.0.8
 */
public class TransactionDTOTest {

    private TransactionDTO transactionDTO;

    /**
     * Sets up the test environment before each test.
     */
    @Before
    public void setUp() {
        transactionDTO = new TransactionDTO();
    }

    /**
     * Tests the default constructor of {@link TransactionDTO}.
     */
    @Test
    public void testDefaultConstructor() {
        assertNotNull(transactionDTO);
    }

    /**
     * Tests the {@link TransactionDTO#setId(int)} and {@link TransactionDTO#getId()} methods.
     */
    @Test
    public void testSetAndGetId() {
        transactionDTO.setId(1);
        assertEquals(1, transactionDTO.getId());
    }

    /**
     * Tests the {@link TransactionDTO#setFoodId(int)} and {@link TransactionDTO#getFoodId()} methods.
     */
    @Test
    public void testSetAndGetFoodId() {
        transactionDTO.setFoodId(2);
        assertEquals(2, transactionDTO.getFoodId());
    }

    /**
     * Tests the {@link TransactionDTO#setUserId(int)} and {@link TransactionDTO#getUserId()} methods.
     */
    @Test
    public void testSetAndGetUserId() {
        transactionDTO.setUserId(3);
        assertEquals(3, transactionDTO.getUserId());
    }

    /**
     * Tests the {@link TransactionDTO#setOrderId(Integer)} and {@link TransactionDTO#getOrderId()} methods.
     */
    @Test
    public void testSetAndGetOrderId() {
        transactionDTO.setOrderId(4);
        assertEquals(Integer.valueOf(4), transactionDTO.getOrderId());
    }

    /**
     * Tests the {@link TransactionDTO#setClaimId(Integer)} and {@link TransactionDTO#getClaimId()} methods.
     */
    @Test
    public void testSetAndGetClaimId() {
        transactionDTO.setClaimId(5);
        assertEquals(Integer.valueOf(5), transactionDTO.getClaimId());
    }

    /**
     * Tests the {@link TransactionDTO#setDate(Date)} and {@link TransactionDTO#getDate()} methods.
     */
    @Test
    public void testSetAndGetDate() {
        Date date = new Date();
        transactionDTO.setDate(date);
        assertEquals(date, transactionDTO.getDate());
    }

    /**
     * Tests the {@link TransactionDTO#setType(int)} and {@link TransactionDTO#getType()} methods.
     */
    @Test
    public void testSetAndGetType() {
        transactionDTO.setType(6);
        assertEquals(6, transactionDTO.getType());
    }

    /**
     * Tests the {@link TransactionDTO#setQtyNormal(int)} and {@link TransactionDTO#getQtyNormal()} methods.
     */
    @Test
    public void testSetAndGetQtyNormal() {
        transactionDTO.setQtyNormal(7);
        assertEquals(7, transactionDTO.getQtyNormal());
    }

    /**
     * Tests the {@link TransactionDTO#setQtyDiscount(int)} and {@link TransactionDTO#getQtyDiscount()} methods.
     */
    @Test
    public void testSetAndGetQtyDiscount() {
        transactionDTO.setQtyDiscount(8);
        assertEquals(8, transactionDTO.getQtyDiscount());
    }

    /**
     * Tests the {@link TransactionDTO#setQtyDonation(int)} and {@link TransactionDTO#getQtyDonation()} methods.
     */
    @Test
    public void testSetAndGetQtyDonation() {
        transactionDTO.setQtyDonation(9);
        assertEquals(9, transactionDTO.getQtyDonation());
    }

    /**
     * Tests setting the order ID to null.
     */
    @Test
    public void testSetOrderIdToNull() {
        transactionDTO.setOrderId(null);
        assertEquals(null, transactionDTO.getOrderId());
    }

    /**
     * Tests setting the claim ID to null.
     */
    @Test
    public void testSetClaimIdToNull() {
        transactionDTO.setClaimId(null);
        assertEquals(null, transactionDTO.getClaimId());
    }
}
