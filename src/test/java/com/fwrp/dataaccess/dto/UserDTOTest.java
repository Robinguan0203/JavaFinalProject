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
 * Unit tests for UserDTO class.
 * 
 * @author robin
 */
public class UserDTOTest {
    
    private UserDTO userDTO;

    public UserDTOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        userDTO = new UserDTO();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetId() {
        System.out.println("getId");
        int expResult = 5;
        userDTO.setId(expResult);
        int result = userDTO.getId();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 123;
        userDTO.setId(id);
        assertEquals(id, userDTO.getId());
    }

    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        String expResult = "Carl";
        userDTO.setFirstName(expResult);
        String result = userDTO.getFirstName();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        String firstName = "John";
        userDTO.setFirstName(firstName);
        assertEquals(firstName, userDTO.getFirstName());
    }

    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        String expResult = "Max";
        userDTO.setLastName(expResult);
        String result = userDTO.getLastName();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetLastName() {
        System.out.println("setLastName");
        String lastName = "Doe";
        userDTO.setLastName(lastName);
        assertEquals(lastName, userDTO.getLastName());
    }

    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        String expResult = "test@test.com";
        userDTO.setEmail(expResult);
        String result = userDTO.getEmail();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "john.doe@example.com";
        userDTO.setEmail(email);
        assertEquals(email, userDTO.getEmail());
    }

    @Test
    public void testGetPhone() {
        System.out.println("getPhone");
        String expResult = "1234567";
        userDTO.setPhone(expResult);
        String result = userDTO.getPhone();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetPhone() {
        System.out.println("setPhone");
        String phone = "123-456-7890";
        userDTO.setPhone(phone);
        assertEquals(phone, userDTO.getPhone());
    }

    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        String expResult = "XXXXX12";
        userDTO.setPassword(expResult);
        String result = userDTO.getPassword();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "password123";
        userDTO.setPassword(password);
        assertEquals(password, userDTO.getPassword());
    }

    @Test
    public void testGetType() {
        System.out.println("getType");
        int expResult = 0;
        userDTO.setType(expResult);
        int result = userDTO.getType();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetType() {
        System.out.println("setType");
        int type = 1;
        userDTO.setType(type);
        assertEquals(type, userDTO.getType());
    }

    @Test
    public void testGetOrganization() {
        System.out.println("getOrganization");
        String expResult = "SAPS";
        userDTO.setOrganization("SAPS");
        String result = userDTO.getOrganization();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetOrganization() {
        System.out.println("setOrganization");
        String organization = "SAPS";
        userDTO.setOrganization(organization);
        assertEquals(organization, userDTO.getOrganization());
    }

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