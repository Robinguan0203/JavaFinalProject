/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.fwrp.dataaccess.dto;

import com.fwrp.dataaccess.dto.NotificationDTO;
import com.fwrp.dbService.UserDbService;
import com.fwrp.models.Consumer;
import com.fwrp.models.Notification;
import com.fwrp.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NotificationDTOTest {

    @InjectMocks
    private NotificationDTO notificationDTO;

    @Mock
    private UserDbService userDbService;

    @Before
    public void setUp() {
        notificationDTO = new NotificationDTO();
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(notificationDTO);
    }

    @Test
    public void testSetAndGetId() {
        notificationDTO.setId(1);
        assertEquals(1, notificationDTO.getId());
    }

    @Test
    public void testSetAndGetMethod() {
        notificationDTO.setMethod(2);
        assertEquals(2, notificationDTO.getMethod());
    }

    @Test
    public void testSetAndGetDate() {
        Date date = new Date();
        notificationDTO.setDate(date);
        assertEquals(date, notificationDTO.getDate());
    }

    @Test
    public void testSetAndGetNotification() {
        notificationDTO.setNotification("Test Notification");
        assertEquals("Test Notification", notificationDTO.getNotification());
    }

    @Test
    public void testSetAndGetUserId() {
        notificationDTO.setUserId(101);
        assertEquals(101, notificationDTO.getUserId());
    }

    
}