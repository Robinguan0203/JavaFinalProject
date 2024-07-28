/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.validator;

import java.util.Date;

/**
 *
 * @author robin
 */
public class ExpireDateValidator {
    private String errorMessage;

    public boolean validate(Date newExpireDate) {
        if (newExpireDate == null) {
            return true; // 未输入日期，视为有效
        }
        if (!validateDate(newExpireDate)) {
            return false;
        }
        return true;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    private boolean validateDate(Date newExpireDate) {
        Date currentDate = new Date();
        if (newExpireDate.before(currentDate)) {
            errorMessage = "Expire date must be later than today.";
            return false;
        }
        return true;
    }
}
