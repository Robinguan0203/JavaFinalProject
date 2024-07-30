/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.validator;

import java.util.Date;

/**
 * Validator class for checking the validity of expiration dates.
 * This class provides methods to validate expiration dates ensuring they are not before the current date.
 * 
 * <p>
 * Example use case: Validate an expiration date before storing it in the database.
 * </p>
 * 
 * @Author Robin Guan(041117292)
 * @version 1.0
 * @since 2023-07-30
 */
public class ExpireDateValidator {
    /** Error message to be returned if validation fails. */
    private String errorMessage;

    /**
     * Validates the provided expiration date.
     * 
     * @param newExpireDate the date to validate
     * @return true if the date is valid or not provided, false if the date is invalid
     */
    public boolean validate(Date newExpireDate) {
        if (newExpireDate == null) {
            return true; // 未输入日期，视为有效
        }
        if (!validateDate(newExpireDate)) {
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
     * Checks if the provided date is not before the current date.
     * 
     * @param newExpireDate the date to check
     * @return true if the date is valid, false if the date is before the current date
     */
    private boolean validateDate(Date newExpireDate) {
        Date currentDate = new Date();
        if (newExpireDate.before(currentDate)) {
            errorMessage = "Expire date must be later than today.";
            return false;
        }
        return true;
    }
}
