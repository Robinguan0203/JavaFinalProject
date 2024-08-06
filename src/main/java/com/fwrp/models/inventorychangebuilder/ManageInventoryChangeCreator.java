/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models.inventorychangebuilder;

import com.fwrp.models.Food;
import com.fwrp.models.ManageInventoryChange;
import com.fwrp.models.Retailer;
import com.fwrp.models.User;
import com.fwrp.models.inventorychangebuilder.ManageInventoryChangeBuilder;
import com.fwrp.models.inventorychangebuilder.InventoryChangeCreator;
import com.fwrp.models.inventorychangebuilder.IInventoryChangeBuilder;

/**
 * Concrete creator class for creating ManageInventoryChange objects.
 * Extends the InventoryChangeCreator class.
 * 
 * This class is responsible for creating ManageInventoryChange objects using the builder pattern.
 * It uses an InventoryChangeDirector to construct the ManageInventoryChange object.
 * 
 * @author Robin Guan
 */
public class ManageInventoryChangeCreator extends InventoryChangeCreator{

	/**
     * Creates a ManageInventoryChange object with the specified parameters.
     * 
     * @param food the food item for the inventory change
     * @param user the user (retailer) for the inventory change
     * @param qtyNormal the quantity of normal items
     * @param qtyDiscount the quantity of discounted items
     * @param qtyDonation the quantity of donated items
     * @return the created ManageInventoryChange object
     */
    @Override
    public ManageInventoryChange createInventoryChange(Food food, User user, 
           int qtyNormal, int qtyDiscount, int qtyDonation) {

        ManageInventoryChange manageInventoryChange = null;

        IInventoryChangeBuilder builder = new ManageInventoryChangeBuilder();
        InventoryChangeDirector director = new InventoryChangeDirector(builder);
        director.buildManageInventoryChange(food, (Retailer)user, qtyNormal, qtyDiscount, qtyDonation);
        manageInventoryChange = (ManageInventoryChange) director.build();
        
        return manageInventoryChange;
    }
    
}
