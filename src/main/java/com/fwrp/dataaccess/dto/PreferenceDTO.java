/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dataaccess.dto;

/**
 * Data Transfer Object for user preferences.
 * <p>
 * This class represents a user preference with a unique identifier, user ID, and food ID.
 * </p>
 * 
 * @author Ke Yan
 * @version 2.0
 */
public class PreferenceDTO {

    /**
     * The unique identifier for the preference.
     */
    private int id;

    /**
     * The unique identifier for the user.
     */
    private int userId;

    /**
     * The unique identifier for the food item.
     */
    private int foodId;

    /**
     * Gets the unique identifier for the preference.
     *
     * @return int The preference ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the preference.
     *
     * @param id The preference ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the unique identifier for the user.
     *
     * @return int The user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the unique identifier for the user.
     *
     * @param userId The user ID.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the unique identifier for the food item.
     *
     * @return int The food ID.
     */
    public int getFoodId() {
        return foodId;
    }

    /**
     * Sets the unique identifier for the food item.
     *
     * @param foodId The food ID.
     */
    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    /**
     * Default constructor for the PreferenceDTO class.
     */
    public PreferenceDTO() {

    }
}
