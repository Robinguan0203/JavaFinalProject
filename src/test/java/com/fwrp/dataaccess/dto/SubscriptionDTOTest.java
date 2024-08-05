/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.fwrp.dataaccess.dto;

import com.fwrp.dbService.UserDbService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit tests for the {@link SubscriptionDTO} class.
 * This class tests the constructors and getter/setter methods of the {@link SubscriptionDTO} class.
 * 
 * @version 1.0
 * Since: 17.0.8
 */
@RunWith(MockitoJUnitRunner.class)
public class SubscriptionDTOTest {

    @InjectMocks
    private SubscriptionDTO subscriptionDTO;

    @Mock
    private UserDbService userDbService;

    /**
     * Sets up the test environment before each test.
     */
    @Before
    public void setUp() {
        subscriptionDTO = new SubscriptionDTO();
    }

    /**
     * Tests the default constructor of {@link SubscriptionDTO}.
     */
    @Test
    public void testDefaultConstructor() {
        assertNotNull(subscriptionDTO);
    }

    /**
     * Tests the {@link SubscriptionDTO#setId(int)} and {@link SubscriptionDTO#getId()} methods.
     */
    @Test
    public void testSetAndGetId() {
        subscriptionDTO.setId(1);
        assertEquals(1, subscriptionDTO.getId());
    }

     /**
     * Tests the {@link SubscriptionDTO#setMethod(int)} and {@link SubscriptionDTO#getMethod()} methods.
     */
    @Test
    public void testSetAndGetMethod() {
        subscriptionDTO.setMethod(2);
        assertEquals(2, subscriptionDTO.getMethod());
    }

    /**
     * Tests the {@link SubscriptionDTO#setUserId(int)} and {@link SubscriptionDTO#getUserId()} methods.
     */
    @Test
    public void testSetAndGetDate() {
        Date date = new Date();
        subscriptionDTO.setUserId(1);
        assertEquals(1, subscriptionDTO.getUserId());
    }


    
}