/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.validator;

/**
 * Validator class for validating the "is surplus" status of food.
 * This class provides a method to validate if the input string for surplus status is valid.
 * 
 * <p>
 * Example use case: Validate the input for surplus status before updating it in the database.
 * </p>
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 2023-07-30
 */
public class IsSurplusValidator {
    /** Error message to be returned if validation fails. */
    private String errorMessage;

     /**
     * Validates the provided surplus status string.
     * 
     * @param newIsSurplusStr the surplus status string to validate
     * @return true if the input is valid ("0" or "1"), false otherwise
     */
    public boolean validate(String newIsSurplusStr) {
        if (newIsSurplusStr == null || newIsSurplusStr.isEmpty()) {
            errorMessage = "Input cannot be empty.";
            return false;
        }
        if (!newIsSurplusStr.equals("0") && !newIsSurplusStr.equals("1")) {
            errorMessage = "Input must be true or false.";
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
}
