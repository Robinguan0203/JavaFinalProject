/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.validator;


/**
 * Validator class for checking the validity of food expiration days and food IDs.
 * This class provides methods to validate that the food ID is positive and expiration days are at least 1.
 * 
 * <p>
 * Example use case: Validate food ID and expiration days before updating or inserting food data into the database.
 * </p>
 * 
 * 
 * @Author Robin Guan(041117292)
 * @version 1.0
 * @since 2023-07-30
 * 
 */
public class FoodExpireDaysValidator {
    /** Error message to be returned if validation fails. */
    private String errorMessage;

    /**
     * Validates the provided food ID and expiration days.
     * 
     * @param foodId the ID of the food to validate
     * @param expireDays the expiration days to validate
     * @return true if both food ID and expiration days are valid, false otherwise
     */
    public boolean validate(int foodId, int expireDays) {
        if (!validateFoodId(foodId)) {
            return false;
        }
        if (!validateExpireDays(expireDays)) {
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
     * Checks if the provided expiration days are at least 1.
     * 
     * @param expireDays the expiration days to check
     * @return true if the expiration days are greater than or equal to 1, false otherwise
     */
    private boolean validateExpireDays(int expireDays) {
        if (expireDays < 1) {
            errorMessage = "Expire days must be greater than or equal to 1.";
            return false;
        }
        return true;
    }
}
