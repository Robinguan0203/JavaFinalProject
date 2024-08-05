/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.fwrp.dataaccess.dto;

import com.fwrp.models.Retailer;
import com.fwrp.models.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the {@link UserDTO} class.
 * This class tests the getter and setter methods of the {@link UserDTO} class.
 * 
 * @version 1.0
 * Since: 17.0.8
 */
public class UserDTOTest {
    
    private UserDTO userDTO;

    /**
     * Default constructor for the {@link UserDTOTest} class.
     */
    public UserDTOTest() {
    }
    
    /**
     * Sets up the test environment before any tests are run.
     */
    @BeforeClass
    public static void setUpClass() {
    }
    
    /**
     * Cleans up the test environment after all tests have been run.
     */
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     * Sets up the test environment before each test.
     */
    @Before
    public void setUp() {
        userDTO = new UserDTO();
    }
    
    /**
     * Cleans up the test environment after each test.
     */
    @After
    public void tearDown() {
    }

    /**
     * Tests the {@link UserDTO#getId()} method.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        int expResult = 5;
        userDTO.setId(expResult);
        int result = userDTO.getId();
        assertEquals(expResult, result);
    }

    /**
     * Tests the {@link UserDTO#setId(int)} method.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 123;
        userDTO.setId(id);
        assertEquals(id, userDTO.getId());
    }

    /**
     * Tests the {@link UserDTO#getFirstName()} method.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        String expResult = "Carl";
        userDTO.setFirstName(expResult);
        String result = userDTO.getFirstName();
        assertEquals(expResult, result);
    }

    /**
     * Tests the {@link UserDTO#setFirstName(String)} method.
     */
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        String firstName = "John";
        userDTO.setFirstName(firstName);
        assertEquals(firstName, userDTO.getFirstName());
    }

    /**
     * Tests the {@link UserDTO#getLastName()} method.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        String expResult = "Max";
        userDTO.setLastName(expResult);
        String result = userDTO.getLastName();
        assertEquals(expResult, result);
    }

    /**
     * Tests the {@link UserDTO#setLastName(String)} method.
     */
    @Test
    public void testSetLastName() {
        System.out.println("setLastName");
        String lastName = "Doe";
        userDTO.setLastName(lastName);
        assertEquals(lastName, userDTO.getLastName());
    }

    /**
     * Tests the {@link UserDTO#getEmail()} method.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        String expResult = "test@test.com";
        userDTO.setEmail(expResult);
        String result = userDTO.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Tests the {@link UserDTO#setEmail(String)} method.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "john.doe@example.com";
        userDTO.setEmail(email);
        assertEquals(email, userDTO.getEmail());
    }

    /**
     * Tests the {@link UserDTO#getPhone()} method.
     */
    @Test
    public void testGetPhone() {
        System.out.println("getPhone");
        String expResult = "1234567";
        userDTO.setPhone(expResult);
        String result = userDTO.getPhone();
        assertEquals(expResult, result);
    }

    /**
     * Tests the {@link UserDTO#setPhone(String)} method.
     */
    @Test
    public void testSetPhone() {
        System.out.println("setPhone");
        String phone = "123-456-7890";
        userDTO.setPhone(phone);
        assertEquals(phone, userDTO.getPhone());
    }

    /**
     * Tests the {@link UserDTO#getPassword()} method.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        String expResult = "XXXXX12";
        userDTO.setPassword(expResult);
        String result = userDTO.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Tests the {@link UserDTO#setPassword(String)} method.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "password123";
        userDTO.setPassword(password);
        assertEquals(password, userDTO.getPassword());
    }

    /**
     * Tests the {@link UserDTO#getType()} method.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        int expResult = 0;
        userDTO.setType(expResult);
        int result = userDTO.getType();
        assertEquals(expResult, result);
    }

     /**
     * Tests the {@link UserDTO#setType(int)} method.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        int type = 1;
        userDTO.setType(type);
        assertEquals(type, userDTO.getType());
    }

    /**
     * Tests the {@link UserDTO#getOrganization()} method.
     */
    @Test
    public void testGetOrganization() {
        System.out.println("getOrganization");
        String expResult = "SAPS";
        userDTO.setOrganization("SAPS");
        String result = userDTO.getOrganization();
        assertEquals(expResult, result);
    }

    /**
     * Tests the {@link UserDTO#setOrganization(String)} method.
     */
    @Test
    public void testSetOrganization() {
        System.out.println("setOrganization");
        String organization = "SAPS";
        userDTO.setOrganization(organization);
        assertEquals(organization, userDTO.getOrganization());
    }

    /**
     * Tests the {@link UserDTO#transferToUser(User)} method.
     */
    @Test
    public void testTransferToUser() {
        System.out.println("transferToUser");
        User user = new Retailer();
        userDTO.setId(1);
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setEmail("john.doe@example.com");
        userDTO.setPhone("123-456-7890");
        userDTO.setPassword("password123");
        userDTO.setType(1);
        userDTO.setOrganization("SAOS");

        User result = userDTO.transferToUser(user);
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("john.doe@example.com", result.getEmail());
        assertEquals("123-456-7890", result.getPhone());
        assertEquals("password123", result.getPassword());
        assertEquals(1, result.getType());
        assertEquals("SAOS", result.getOrganization());
    }
}