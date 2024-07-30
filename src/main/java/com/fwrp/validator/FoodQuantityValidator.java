/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.validator;

/**
 * Validator class for checking the validity of food quantity and food IDs.
 * This class provides methods to validate that the food ID is positive and quantity is greater than zero.
 * 
 * <p>
 * Example use case: Validate food ID and quantity before updating or inserting food data into the database.
 * </p>
 * 
 * @Author Robin Guan(041117292)
 * @version 1.0
 * @since 2023-07-30
 * 
 */
public class FoodQuantityValidator {
    /** Error message to be returned if validation fails. */
    private String errorMessage;

    /**
     * Validates the provided food ID and quantity.
     * 
     * @param foodId the ID of the food to validate
     * @param quantity the quantity to validate
     * @return true if both food ID and quantity are valid, false otherwise
     */
    public boolean validate(int foodId, int quantity) {
        if (!validateFoodId(foodId)) {
            return false;
        }
        if (!validateQuantity(quantity)) {
            return false;
        }
        return true;
    }

    /**
     * Retrieves the error message if validation fails.
     * 
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Checks if the provided food ID is positive.
     * 
     * @param foodId the ID of the food to check
     * @return true if the food ID is positive, false otherwise
     */
    private boolean validateFoodId(int foodId) {
        if (foodId <= 0) {
            errorMessage = "Food ID must be positive.";
            return false;
        }
        return true;
    }

    /**
     * Checks if the provided quantity is greater than zero.
     * 
     * @param quantity the quantity to check
     * @return true if the quantity is greater than zero, false otherwise
     */
    private boolean validateQuantity(int quantity) {
        if (quantity <= 0) {
            errorMessage = "Quantity cannot be negative or zero.";
            return false;
        }
        return true;
    }
}
