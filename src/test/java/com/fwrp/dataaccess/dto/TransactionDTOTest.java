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

public class TransactionDTOTest {

    private TransactionDTO transactionDTO;

    @Before
    public void setUp() {
        transactionDTO = new TransactionDTO();
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(transactionDTO);
    }

    @Test
    public void testSetAndGetId() {
        transactionDTO.setId(1);
        assertEquals(1, transactionDTO.getId());
    }

    @Test
    public void testSetAndGetFoodId() {
        transactionDTO.setFoodId(2);
        assertEquals(2, transactionDTO.getFoodId());
    }

    @Test
    public void testSetAndGetUserId() {
        transactionDTO.setUserId(3);
        assertEquals(3, transactionDTO.getUserId());
    }

    @Test
    public void testSetAndGetOrderId() {
        transactionDTO.setOrderId(4);
        assertEquals(Integer.valueOf(4), transactionDTO.getOrderId());
    }

    @Test
    public void testSetAndGetClaimId() {
        transactionDTO.setClaimId(5);
        assertEquals(Integer.valueOf(5), transactionDTO.getClaimId());
    }

    @Test
    public void testSetAndGetDate() {
        Date date = new Date();
        transactionDTO.setDate(date);
        assertEquals(date, transactionDTO.getDate());
    }

    @Test
    public void testSetAndGetType() {
        transactionDTO.setType(6);
        assertEquals(6, transactionDTO.getType());
    }

    @Test
    public void testSetAndGetQtyNormal() {
        transactionDTO.setQtyNormal(7);
        assertEquals(7, transactionDTO.getQtyNormal());
    }

    @Test
    public void testSetAndGetQtyDiscount() {
        transactionDTO.setQtyDiscount(8);
        assertEquals(8, transactionDTO.getQtyDiscount());
    }

    @Test
    public void testSetAndGetQtyDonation() {
        transactionDTO.setQtyDonation(9);
        assertEquals(9, transactionDTO.getQtyDonation());
    }

    @Test
    public void testSetOrderIdToNull() {
        transactionDTO.setOrderId(null);
        assertEquals(null, transactionDTO.getOrderId());
    }

    @Test
    public void testSetClaimIdToNull() {
        transactionDTO.setClaimId(null);
        assertEquals(null, transactionDTO.getClaimId());
    }
}
