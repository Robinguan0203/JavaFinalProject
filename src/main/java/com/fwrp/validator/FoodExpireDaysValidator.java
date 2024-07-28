/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.validator;


/**
 *
 * @author robin
 */
public class FoodExpireDaysValidator {
    private String errorMessage;

    public boolean validate(int foodId, int expireDays) {
        if (!validateFoodId(foodId)) {
            return false;
        }
        if (!validateExpireDays(expireDays)) {
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

    private boolean validateExpireDays(int expireDays) {
        if (expireDays < 1) {
            errorMessage = "Expire days must be greater than or equal to 1.";
            return false;
        }
        return true;
    }
}
