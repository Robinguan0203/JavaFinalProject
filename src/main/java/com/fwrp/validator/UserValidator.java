/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.validator;

import java.util.regex.Pattern;

/**
 *
 * @author robin
 */
public class UserValidator {
    private String errorMessage;
    private static final String PHONE_REGEX = "\\d+";
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

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

    public String getErrorMessage() {
        return errorMessage;
    }

    private boolean validateFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            errorMessage = "First name cannot be empty.";
            return false;
        }
        return true;
    }

    private boolean validateLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            errorMessage = "Last name cannot be empty.";
            return false;
        }
        return true;
    }

    private boolean validatePhone(String phone) {
        if (phone == null || !PHONE_PATTERN.matcher(phone).matches()) {
            errorMessage = "Phone number must be numeric.";
            return false;
        }
        return true;
    }

    private boolean validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            errorMessage = "Email cannot be empty.";
            return false;
        }
        return true;
    }

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

    private boolean validateOrganization(String type, String organization) {
        if (!"3".equals(type) && (organization == null || organization.trim().isEmpty())) {
            errorMessage = "Organization name cannot be empty for Retailer or Charity.";
            return false;
        }
        return true;
    }
}
