package com.fwrp.models;

import com.fwrp.dataaccess.dto.ExpireInfoDTO;
import com.fwrp.dbService.InventoryDbService;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Represents a Charity user, extending the User class.
 * This class provides specific implementations for login and logout methods,
 * and includes functionality to create inventory changes (claims).
 */
public class Charity extends User {
	/**
     * Creates an inventory change (claim) for the charity user.
     * 
     * @param food The food item for which the inventory change is being created.
     * @param qtyNormal The normal quantity of the food item.
     * @param qtyDiscount The discounted quantity of the food item.
     * @param qtyDonation The donated quantity of the food item.
     * @return Claim The created claim representing the inventory change.
     */
    public Claim createInventorychange(Food food, 
            int qtyNormal, int qtyDiscount, int qtyDonation){
        
        ClaimCreator creator = new ClaimCreator();
        Claim claim = creator.createInventoryChange(food, this, qtyNormal, qtyDiscount, qtyDonation);
        return claim;
    }    
}
