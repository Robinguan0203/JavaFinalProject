package com.fwrp.models;

import com.fwrp.dataaccess.dto.ExpireInfoDTO;
import com.fwrp.dbService.InventoryDbService;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Represents a Consumer user, extending the User class.
 * This class provides functionality to create inventory changes (orders).
 */
public class Consumer extends User {

	/**
     * Creates an inventory change (order) for the consumer user.
     * 
     * @param food The food item for which the inventory change is being created.
     * @param qtyNormal The normal quantity of the food item.
     * @param qtyDiscount The discounted quantity of the food item.
     * @param qtyDonation The donated quantity of the food item.
     * @return Order The created order representing the inventory change.
     */
     public Order createInventorychange(Food food, 
            int qtyNormal, int qtyDiscount, int qtyDonation){
        
        OrderCreator creator = new OrderCreator();
        Order order = creator.createInventoryChange(food, this, qtyNormal, qtyDiscount, qtyDonation);
        return order;
    }    
}
