/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models.inventorychangebuilder;

import com.fwrp.models.Food;
import com.fwrp.models.InventoryChange;
import com.fwrp.models.ManageInventoryChange;
import com.fwrp.models.Retailer;
import com.fwrp.models.User;
import com.fwrp.models.inventorychangebuilder.IInventoryChangeBuilder;
import java.util.Date;

/**
 * Builder class for constructing ManageInventoryChange objects using the builder pattern.
 * Implements the IInventoryChangeBuilder interface.
 * 
 * This class is responsible for setting the properties of a ManageInventoryChange object.
 * It provides methods to set the food item, user, date, and quantities of normal, discounted, and donated items.
 * The constructed ManageInventoryChange object can be retrieved using the getInventoryChange method.
 * 
 * @author Robin Guan
 */
public class ManageInventoryChangeBuilder implements IInventoryChangeBuilder {
	
	/**
     * The ManageInventoryChange object being constructed.
     */
    private ManageInventoryChange manageInventoryChange;

	/**
     * Constructor initializes a new ManageInventoryChange object.
     */
    public ManageInventoryChangeBuilder(){
        manageInventoryChange = new ManageInventoryChange();
    }

	/**
     * Sets the food item for the inventory change.
     * 
     * @param food the food item to set
     * @return the current instance of ManageInventoryChangeBuilder
     */
    @Override
    public IInventoryChangeBuilder setFood(Food food) {
        manageInventoryChange.setFood(food);
        return this;
    }
	
	/**
     * Sets the user (retailer) for the inventory change.
     * 
     * @param user the user to set
     * @return the current instance of ManageInventoryChangeBuilder
     */
    @Override
    public IInventoryChangeBuilder setUser(User user) {
        manageInventoryChange.setRetailer((Retailer) user);
        return this;
    }
	
	/**
     * Sets the date for the inventory change.
     * 
     * @param date the date to set
     * @return the current instance of ManageInventoryChangeBuilder
     */
    @Override
    public IInventoryChangeBuilder setDate(Date date) {
        manageInventoryChange.setDate(date);
        return this;
    }
	
	/**
     * Sets the quantity of normal items for the inventory change.
     * 
     * @param qtyNormal the quantity of normal items to set
     * @return the current instance of ManageInventoryChangeBuilder
     */
    @Override
    public IInventoryChangeBuilder setQtyNormal(int qtyNormal) {
        manageInventoryChange.setQtyNormal(qtyNormal);
        return this;
    }
	
	/**
     * Sets the quantity of discounted items for the inventory change.
     * 
     * @param qtyDiscount the quantity of discounted items to set
     * @return the current instance of ManageInventoryChangeBuilder
     */
    @Override
    public IInventoryChangeBuilder setQtyDiscount(int qtyDiscount) {
        manageInventoryChange.setQtyDiscount(qtyDiscount);
        return this;
    }
	
	/**
     * Sets the quantity of donated items for the inventory change.
     * 
     * @param qtyDonation the quantity of donated items to set
     * @return the current instance of ManageInventoryChangeBuilder
     */
    @Override
    public IInventoryChangeBuilder setQtyDonation(int qtyDonation) {
        manageInventoryChange.setQtyDonation(qtyDonation);
        return this;
    }
	
	/**
     * Retrieves the constructed ManageInventoryChange object.
     * 
     * @return the constructed ManageInventoryChange object
     */
    @Override
    public InventoryChange getInventoryChange() {
        return manageInventoryChange;
    }
    
    
}
