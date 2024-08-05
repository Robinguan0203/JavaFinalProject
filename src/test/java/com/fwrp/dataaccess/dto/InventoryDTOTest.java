/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.fwrp.dataaccess.dto;

import com.fwrp.dataaccess.dto.InventoryDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the {@link InventoryDTO} class.
 * This class tests the constructors and getter/setter methods of the {@link InventoryDTO} class.
 * 
 * @version 1.0
 * Since: 17.0.8
 */
public class InventoryDTOTest {
    
    private InventoryDTO inventoryDTO;
    
    /**
     * Sets up the test environment before each test.
     */
    @Before
    public void setUp() {
        inventoryDTO = new InventoryDTO();
    }
    
    /**
     * Tests the default constructor of {@link InventoryDTO}.
     */
    @Test
    public void testDefaultConstructor() {
        assertNotNull(inventoryDTO);
    }
    
    /**
     * Tests the parameterized constructor of {@link InventoryDTO}.
     */
    @Test
    public void testParameterizedConstructor() {
        inventoryDTO = new InventoryDTO(1, 101, 50, 20, 10);
        
        assertEquals(1, inventoryDTO.getId());
        assertEquals(101, inventoryDTO.getFoodId());
        assertEquals(50, inventoryDTO.getQtyNormal());
        assertEquals(20, inventoryDTO.getQtyDiscount());
        assertEquals(10, inventoryDTO.getQtydonation());
    }
    
    /**
     * Tests the {@link InventoryDTO#setId(int)} and {@link InventoryDTO#getId()} methods.
     */
    @Test
    public void testSetAndGetId() {
        inventoryDTO.setId(1);
        assertEquals(1, inventoryDTO.getId());
    }

    /**
     * Tests the {@link InventoryDTO#setFoodId(int)} and {@link InventoryDTO#getFoodId()} methods.
     */
    @Test
    public void testSetAndGetFoodId() {
        inventoryDTO.setFoodId(101);
        assertEquals(101, inventoryDTO.getFoodId());
    }

    /**
     * Tests the {@link InventoryDTO#setQtyNormal(int)} and {@link InventoryDTO#getQtyNormal()} methods.
     */
    @Test
    public void testSetAndGetQtyNormal() {
        inventoryDTO.setQtyNormal(50);
        assertEquals(50, inventoryDTO.getQtyNormal());
    }

    /**
     * Tests the {@link InventoryDTO#setQtyDiscount(int)} and {@link InventoryDTO#getQtyDiscount()} methods.
     */
    @Test
    public void testSetAndGetQtyDiscount() {
        inventoryDTO.setQtyDiscount(20);
        assertEquals(20, inventoryDTO.getQtyDiscount());
    }

    /**
     * Tests the {@link InventoryDTO#setQtydonation(int)} and {@link InventoryDTO#getQtydonation()} methods.
     */
    @Test
    public void testSetAndGetQtydonation() {
        inventoryDTO.setQtydonation(10);
        assertEquals(10, inventoryDTO.getQtydonation());
    }
}
