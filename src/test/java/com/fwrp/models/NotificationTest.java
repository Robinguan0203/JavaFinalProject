/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.fwrp.models;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;

public class NotificationTest {

    private Notification notification;
    private User user;

    @Before
    public void setUp() {
        // 初始化 Notification 对象和 User 对象
        notification = new Notification();
        user = new Retailer(); // Assuming User has a default constructor
    }

    @Test
    public void testGetId() {
        int expResult = 1;
        notification.setId(expResult);
        int result = notification.getId();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetId() {
        int id = 2;
        notification.setId(id);
        assertEquals(id, notification.getId());
    }

    @Test
    public void testGetMethod() {
        int expResult = 1;
        notification.setMethod(expResult);
        int result = notification.getMethod();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetMethod() {
        int method = 2;
        notification.setMethod(method);
        assertEquals(method, notification.getMethod());
    }

    @Test
    public void testGetDate() {
        Date expResult = new Date();
        notification.setDate(expResult);
        Date result = notification.getDate();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetDate() {
        Date date = new Date();
        notification.setDate(date);
        assertEquals(date, notification.getDate());
    }

    @Test
    public void testGetNotification() {
        String expResult = "Sample Notification";
        notification.setNotification(expResult);
        String result = notification.getNotification();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetNotification() {
        String notificationText = "Another Notification";
        notification.setNotification(notificationText);
        assertEquals(notificationText, notification.getNotification());
    }

    @Test
    public void testGetUser() {
        notification.setUser(user);
        User result = notification.getUser();
        assertEquals(user, result);
    }

    @Test
    public void testSetUser() {
        notification.setUser(user);
        assertEquals(user, notification.getUser());
    }
}
