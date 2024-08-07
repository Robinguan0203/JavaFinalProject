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
 * Unit tests for the {@link PreferenceDTO} class.
 * This class tests the constructors and getter/setter methods of the {@link PreferenceDTO} class.
 * 
 * @version 2.0
 * @author Ke Yan
 */
@RunWith(MockitoJUnitRunner.class)
public class PreferenceDTOTest {

    @InjectMocks
    private PreferenceDTO preferenceDTO;

    @Mock
    private UserDbService userDbService;

    /**
     * Sets up the test environment before each test.
     */
    @Before
    public void setUp() {
        preferenceDTO = new PreferenceDTO();
    }

    /**
     * Tests the default constructor of {@link PreferenceDTO}.
     */
    @Test
    public void testDefaultConstructor() {
        assertNotNull(preferenceDTO);
    }

    /**
     * Tests the {@link PreferenceDTO#setId(int)} and {@link PreferenceDTO#getId()} methods.
     */
    @Test
    public void testSetAndGetId() {
        preferenceDTO.setId(1);
        assertEquals(1, preferenceDTO.getId());
    }

    /**
     * Tests the {@link PreferenceDTO#setFoodId(int)} and {@link PreferenceDTO#getFoodId()} methods.
     */
    @Test
    public void testSetAndGetFoodId() {
        preferenceDTO.setFoodId(2);
        assertEquals(2, preferenceDTO.getFoodId());
    }

    /**
     * Tests the {@link PreferenceDTO#setUserId(int)} and {@link PreferenceDTO#getUserId()} methods.
     */
    @Test
    public void testSetAndGetUserId() {
        preferenceDTO.setUserId(1);
        assertEquals(1, preferenceDTO.getUserId());
    }
}
