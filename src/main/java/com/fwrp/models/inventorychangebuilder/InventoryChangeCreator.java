/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models.inventorychangebuilder;

import com.fwrp.models.Food;
import com.fwrp.models.InventoryChange;
import com.fwrp.models.User;


/**
 * Abstract class for creating InventoryChange objects.
 * Subclasses should implement the createInventoryChange method to provide specific creation logic.
 * 
 */
public abstract class InventoryChangeCreator { 

	/**
     * Creates an InventoryChange object with the specified properties.
     * 
     * @param food the food item associated with the inventory change
     * @param user the user associated with the inventory change
     * @param qtyNormal the quantity of normal items
     * @param qtyDiscount the quantity of discounted items
     * @param qtyDonation the quantity of donated items
     * @return the created InventoryChange object
     */
    public abstract InventoryChange createInventoryChange(Food food, User user, 
            int qtyNormal, int qtyDiscount, int qtyDonation);
}
