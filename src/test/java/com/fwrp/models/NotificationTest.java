/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.fwrp.models;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;

/**
 * Unit tests for the {@link Notification} class.
 * Tests the getter and setter methods to ensure proper functionality.
 */
public class NotificationTest {

    private Notification notification;
    private User user;

    /**
     * Sets up the test environment before each test.
     */
    @Before
    public void setUp() {
        notification = new Notification();
        user = new Retailer(); // Assuming User has a default constructor
    }

    /**
     * Test of getId method, of class Notification.
     */
    @Test
    public void testGetId() {
        int expResult = 1;
        notification.setId(expResult);
        int result = notification.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Notification.
     */
    @Test
    public void testSetId() {
        int id = 2;
        notification.setId(id);
        assertEquals(id, notification.getId());
    }

    /**
     * Test of getMethod method, of class Notification.
     */
    @Test
    public void testGetMethod() {
        int expResult = 1;
        notification.setMethod(expResult);
        int result = notification.getMethod();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMethod method, of class Notification.
     */
    @Test
    public void testSetMethod() {
        int method = 2;
        notification.setMethod(method);
        assertEquals(method, notification.getMethod());
    }

    /**
     * Test of getDate method, of class Notification.
     */
    @Test
    public void testGetDate() {
        Date expResult = new Date();
        notification.setDate(expResult);
        Date result = notification.getDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDate method, of class Notification.
     */
    @Test
    public void testSetDate() {
        Date date = new Date();
        notification.setDate(date);
        assertEquals(date, notification.getDate());
    }

    /**
     * Test of getNotification method, of class Notification.
     */
    @Test
    public void testGetNotification() {
        String expResult = "Sample Notification";
        notification.setNotification(expResult);
        String result = notification.getNotification();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNotification method, of class Notification.
     */
    @Test
    public void testSetNotification() {
        String notificationText = "Another Notification";
        notification.setNotification(notificationText);
        assertEquals(notificationText, notification.getNotification());
    }

    /**
     * Test of getUser method, of class Notification.
     */
    @Test
    public void testGetUser() {
        notification.setUser(user);
        User result = notification.getUser();
        assertEquals(user, result);
    }

    /**
     * Test of setUser method, of class Notification.
     */
    @Test
    public void testSetUser() {
        notification.setUser(user);
        assertEquals(user, notification.getUser());
    }
}
