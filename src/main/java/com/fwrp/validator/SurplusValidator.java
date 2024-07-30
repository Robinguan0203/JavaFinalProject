/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.validator;

import com.fwrp.models.Food;
import java.util.HashMap;

/**
 * Validator class for checking the validity of surplus quantities for a specific food item.
 * This class ensures that the quantities to discount and donate are valid based on the available surplus.
 * 
 * <p>
 * Example use case: Validate quantities for discounting or donating food before performing the operations.
 * </p>
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 2023-07-30
 */
public class SurplusValidator {
    /** Error message to be returned if validation fails. */
    private String errorMessage;

     /**
     * Validates the quantities to discount and donate for a specific food item.
     * 
     * @param foodId the ID of the food item to validate
     * @param qtyToDiscount the quantity to be discounted
     * @param qtyToDonate the quantity to be donated
     * @param foodExpireQtyMap a map containing food items and their corresponding quantities
     * @return true if the quantities are valid, false otherwise
     */
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

    /**
     * Retrieves the error message if validation fails.
     * 
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}
