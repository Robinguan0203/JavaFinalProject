/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.validator;

/**
 *
 * @author robin
 */
public class IsSurplusValidator {
    private String errorMessage;

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

    public String getErrorMessage() {
        return errorMessage;
    }
}
