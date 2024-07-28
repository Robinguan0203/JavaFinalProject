/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.validator;

/**
 *
 * @author robin
 */
public class FoodValidator {
    private String errorMessage;

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

    public String getErrorMessage() {
        return errorMessage;
    }

    private boolean validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            errorMessage = "Name cannot be empty.";
            return false;
        }
        return true;
    }

    private boolean validateExpireDays(int expireDays) {
        if (expireDays <= 0) {
            errorMessage = "Expire days cannot be negative or zero.";
            return false;
        }
        return true;
    }

    private boolean validateUnitPrice(double unitPrice) {
        if (unitPrice < 0) {
            errorMessage = "Unit price cannot be negative.";
            return false;
        }
        return true;
    }

    private boolean validateDiscount(double discount) {
        if (discount < 0 || discount > 1) {
            errorMessage = "Discount must be between 0 and 1.";
            return false;
        }
        return true;
    }
}