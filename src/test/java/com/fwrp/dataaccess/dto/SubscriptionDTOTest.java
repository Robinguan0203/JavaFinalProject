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

@RunWith(MockitoJUnitRunner.class)
public class SubscriptionDTOTest {

    @InjectMocks
    private SubscriptionDTO subscriptionDTO;

    @Mock
    private UserDbService userDbService;

    @Before
    public void setUp() {
        subscriptionDTO = new SubscriptionDTO();
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(subscriptionDTO);
    }

    @Test
    public void testSetAndGetId() {
        subscriptionDTO.setId(1);
        assertEquals(1, subscriptionDTO.getId());
    }

    @Test
    public void testSetAndGetMethod() {
        subscriptionDTO.setMethod(2);
        assertEquals(2, subscriptionDTO.getMethod());
    }

    @Test
    public void testSetAndGetDate() {
        Date date = new Date();
        subscriptionDTO.setUserId(1);
        assertEquals(1, subscriptionDTO.getUserId());
    }


    
}