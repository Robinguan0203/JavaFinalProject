/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.validator;

import com.fwrp.models.Food;
import java.util.HashMap;

/**
 *
 * @author robin
 */
public class SurplusValidator {
    private String errorMessage;

    public boolean validate(int foodId, int qtyToDiscount, int qtyToDonate, HashMap<Food, Integer[]> foodExpireQtyMap) {
        Food targetFood = null;
        for (Food food : foodExpireQtyMap.keySet()) {
            if (food.getId() == foodId) {
                targetFood = food;
                break;
            }
        }

        if (targetFood == null) {
            errorMessage = "Food ID not found.";
            return false;
        }

        Integer[] qtyArray = foodExpireQtyMap.get(targetFood);
        int surplusQty = qtyArray[0];
        int qtyDiscounted = qtyArray[2];
        int qtyDonated = qtyArray[3];

        if ((qtyToDiscount < 0 || qtyToDonate < 0) || (qtyToDiscount + qtyToDonate > surplusQty - qtyDiscounted - qtyDonated)) {
            //errorMessage = "foodId=" + targetFood.getId() + "; surplusQty=" + String.valueOf(surplusQty) + "; qtyDiscounted=" + String.valueOf(qtyDiscounted) + "; qtyDonated=" + String.valueOf(qtyDonated)
            //        + "; qtyToDiscount=" + String.valueOf(qtyToDiscount) + "; qtyToDonate=" + String.valueOf(qtyToDonate);
            errorMessage = "Invalid quantities: quantity to discount and quantity to donate must be non-negative and their sum must not exceed available surplus quantity.";
            return false;
        }

        return true;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
