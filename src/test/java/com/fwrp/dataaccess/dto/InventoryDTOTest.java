/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.fwrp.dataaccess.dto;

import com.fwrp.dataaccess.dto.InventoryDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryDTOTest {
    
    private InventoryDTO inventoryDTO;
    
    @Before
    public void setUp() {
        inventoryDTO = new InventoryDTO();
    }
    
    @Test
    public void testDefaultConstructor() {
        assertNotNull(inventoryDTO);
    }
    
    @Test
    public void testParameterizedConstructor() {
        inventoryDTO = new InventoryDTO(1, 101, 50, 20, 10);
        
        assertEquals(1, inventoryDTO.getId());
        assertEquals(101, inventoryDTO.getFoodId());
        assertEquals(50, inventoryDTO.getQtyNormal());
        assertEquals(20, inventoryDTO.getQtyDiscount());
        assertEquals(10, inventoryDTO.getQtydonation());
    }
    
    @Test
    public void testSetAndGetId() {
        inventoryDTO.setId(1);
        assertEquals(1, inventoryDTO.getId());
    }

    @Test
    public void testSetAndGetFoodId() {
        inventoryDTO.setFoodId(101);
        assertEquals(101, inventoryDTO.getFoodId());
    }

    @Test
    public void testSetAndGetQtyNormal() {
        inventoryDTO.setQtyNormal(50);
        assertEquals(50, inventoryDTO.getQtyNormal());
    }

    @Test
    public void testSetAndGetQtyDiscount() {
        inventoryDTO.setQtyDiscount(20);
        assertEquals(20, inventoryDTO.getQtyDiscount());
    }

    @Test
    public void testSetAndGetQtydonation() {
        inventoryDTO.setQtydonation(10);
        assertEquals(10, inventoryDTO.getQtydonation());
    }
}
