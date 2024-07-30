/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.validator;

import java.util.regex.Pattern;

/**
 * Validator class for user registration details.
 * This class validates various attributes of a user including personal details, contact information, and authentication credentials.
 * 
 * <p>
 * Example use case: Ensure that user registration inputs are valid before creating a new user account.
 * </p>
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 2023-07-30
 */
public class UserValidator {
    /** Error message to be returned if validation fails. */
    private String errorMessage;
    
    /** Regular expression for validating phone numbers. */
    private static final String PHONE_REGEX = "\\d+";
    
    /** Pattern for validating phone numbers. */
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

    /**
     * Validates user registration details.
     * 
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param phone the phone number of the user
     * @param email the email address of the user
     * @param password the password for the user account
     * @param rePassword the password confirmation
     * @param type the type of user (e.g., Retailer, Consumer, Charity)
     * @param organization the organization name (relevant for certain user types)
     * @return true if all fields are valid, false otherwise
     */
    public boolean validate(String firstName, String lastName, String phone, String email, String password, String rePassword, String type, String organization) {
        if (!validateFirstName(firstName)) {
            return false;
        }
        if (!validateLastName(lastName)) {
            return false;
        }
        if (!validatePhone(phone)) {
            return false;
        }
        if (!validateEmail(email)) {
            return false;
        }
        if (!validatePassword(password, rePassword)) {
            return false;
        }
        if (!validateType(type)) {
            return false;
        }
        if (!validateOrganization(type, organization)) {
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
     * Validates the first name of the user.
     * 
     * @param firstName the first name to validate
     * @return true if the first name is not empty, false otherwise
     */
    private boolean validateFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            errorMessage = "First name cannot be empty.";
            return false;
        }
        return true;
    }

    /**
     * Validates the last name of the user.
     * 
     * @param lastName the last name to validate
     * @return true if the last name is not empty, false otherwise
     */
    private boolean validateLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            errorMessage = "Last name cannot be empty.";
            return false;
        }
        return true;
    }

    /**
     * Validates the phone number of the user.
     * 
     * @param phone the phone number to validate
     * @return true if the phone number is numeric, false otherwise
     */
    private boolean validatePhone(String phone) {
        if (phone == null || !PHONE_PATTERN.matcher(phone).matches()) {
            errorMessage = "Phone number must be numeric.";
            return false;
        }
        return true;
    }

    /**
     * Validates the email address of the user.
     * 
     * @param email the email address to validate
     * @return true if the email address is not empty, false otherwise
     */
    private boolean validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            errorMessage = "Email cannot be empty.";
            return false;
        }
        return true;
    }

    /**
     * Validates the password and its confirmation.
     * 
     * @param password the password to validate
     * @param rePassword the password confirmation
     * @return true if the password is not empty and matches the confirmation, false otherwise
     */
    private boolean validatePassword(String password, String rePassword) {
        if (password == null || password.trim().isEmpty()) {
            errorMessage = "Password cannot be empty.";
            return false;
        }
        if (!password.equals(rePassword)) {
            errorMessage = "Passwords do not match.";
            return false;
        }
        return true;
    }

     /**
     * Validates the user type.
     * 
     * @param type the type of user to validate
     * @return true if the type is a valid number between 1 and 3, false otherwise
     */
    private boolean validateType(String type) {
        try {
            int userType = Integer.parseInt(type);
            if (userType < 1 || userType > 3) {
                errorMessage = "Invalid user type.";
                return false;
            }
        } catch (NumberFormatException e) {
            errorMessage = "User type must be a number.";
            return false;
        }
        return true;
    }

    /**
     * Validates the organization name based on the user type.
     * 
     * @param type the type of user
     * @param organization the organization name to validate
     * @return true if the organization name is not empty for non-consumer types, false otherwise
     */
    private boolean validateOrganization(String type, String organization) {
        if (!"3".equals(type) && (organization == null || organization.trim().isEmpty())) {
            errorMessage = "Organization name cannot be empty for Retailer or Charity.";
            return false;
        }
        return true;
    }
}
