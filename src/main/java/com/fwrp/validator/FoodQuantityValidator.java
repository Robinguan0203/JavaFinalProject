/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.validator;

/**
 *
 * @author robin
 */
public class FoodQuantityValidator {
    private String errorMessage;

    public boolean validate(int foodId, int quantity) {
        if (!validateFoodId(foodId)) {
            return false;
        }
        if (!validateQuantity(quantity)) {
            return false;
        }
        return true;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    private boolean validateFoodId(int foodId) {
        if (foodId <= 0) {
            errorMessage = "Food ID must be positive.";
            return false;
        }
        return true;
    }

    private boolean validateQuantity(int quantity) {
        if (quantity <= 0) {
            errorMessage = "Quantity cannot be negative or zero.";
            return false;
        }
        return true;
    }
}
