/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dataaccess.dto;

public class PreferenceDTO {
    /**
     * The unique identifier for the subscription
     */
    private int id;

    /**
     * The unique identifier for the user
     */
    private int userId;

    /**
     * The method of the subscription
     */
    private int foodId;

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    /**
     * Gets the unique identifier for the subscription.
     *
     * @return int The subscription ID.
     */
    public int getId() {
        return id;
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
     * Sets the unique identifier for the subscription.
     *
     * @param id The subscription ID.
     */
    public void setId(int id) {
        this.id = id;
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
     * Default constructor for the SubscriptionDTO class.
     */
    public PreferenceDTO() {

    }
}
