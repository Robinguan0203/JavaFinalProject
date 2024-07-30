/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.validator;

/**
 * Validator class for validating food data.
 * This class provides methods to validate the name, expiration days, unit price, and discount of a food item.
 * 
 * <p>
 * Example use case: Validate food data before storing it into the database.
 * </p>
 * 
 * @Author Robin Guan(041117292)
 * @version 1.0
 * @since 2023-07-30
 */
public class FoodValidator {
    /** Error message to be returned if validation fails. */
    private String errorMessage;

    /**
     * Validates the provided food data.
     * 
     * @param name the name of the food to validate
     * @param expireDays the number of days until the food expires
     * @param unitPrice the unit price of the food
     * @param discount the discount applied to the food (should be between 0 and 1)
     * @return true if all fields are valid, false otherwise
     */
    public boolean validate(String name, int expireDays, double unitPrice, double discount) {
        if (!validateName(name)) {
            return false;
        }
        if (!validateExpireDays(expireDays)) {
            return false;
        }
        if (!validateUnitPrice(unitPrice)) {
            return false;
        }
        if (!validateDiscount(discount)) {
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
     * Validates the name of the food.
     * 
     * @param name the name to validate
     * @return true if the name is not null and not empty, false otherwise
     */
    private boolean validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            errorMessage = "Name cannot be empty.";
            return false;
        }
        return true;
    }

    /**
     * Validates the expiration days of the food.
     * 
     * @param expireDays the number of days until the food expires
     * @return true if the expiration days is greater than zero, false otherwise
     */
    private boolean validateExpireDays(int expireDays) {
        if (expireDays <= 0) {
            errorMessage = "Expire days cannot be negative or zero.";
            return false;
        }
        return true;
    }

    /**
     * Validates the unit price of the food.
     * 
     * @param unitPrice the unit price to validate
     * @return true if the unit price is not negative, false otherwise
     */
    private boolean validateUnitPrice(double unitPrice) {
        if (unitPrice < 0) {
            errorMessage = "Unit price cannot be negative.";
            return false;
        }
        return true;
    }

    /**
     * Validates the discount of the food.
     * 
     * @param discount the discount to validate (should be between 0 and 1)
     * @return true if the discount is between 0 and 1, false otherwise
     */
    private boolean validateDiscount(double discount) {
        if (discount < 0 || discount > 1) {
            errorMessage = "Discount must be between 0 and 1.";
            return false;
        }
        return true;
    }
}