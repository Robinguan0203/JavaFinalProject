/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models;

import java.util.Date;

/**
 * Interface for building an InventoryChange object.
 * Provides methods to set various properties of the InventoryChange.
 * Implementations of this interface should provide the logic for constructing an InventoryChange object.
 * 
 */
public interface IInventoryChangeBuilder {
	
	/**
     * Sets the food item for the inventory change.
     * 
     * @param food the food item to set
     * @return the builder instance
     */
    IInventoryChangeBuilder setFood(Food food);
	
	/**
     * Sets the user associated with the inventory change.
     * 
     * @param user the user to set
     * @return the builder instance
     */
    IInventoryChangeBuilder setUser(User user);
	
	/**
     * Sets the date of the inventory change.
     * 
     * @param date the date to set
     * @return the builder instance
     */
    IInventoryChangeBuilder setDate(Date date);
	
	/**
     * Sets the quantity of normal items for the inventory change.
     * 
     * @param qtyNormal the quantity of normal items to set
     * @return the builder instance
     */
    IInventoryChangeBuilder setQtyNormal(int qtyNormal);
	
	/**
     * Sets the quantity of discounted items for the inventory change.
     * 
     * @param qtyDiscount the quantity of discounted items to set
     * @return the builder instance
     */
    IInventoryChangeBuilder setQtyDiscount(int qtyDiscount);
	
	/**
     * Sets the quantity of donated items for the inventory change.
     * 
     * @param qtyDonation the quantity of donated items to set
     * @return the builder instance
     */
    IInventoryChangeBuilder setQtyDonation(int qtyDonation);
	
	/**
     * Builds and returns the InventoryChange object.
     * 
     * @return the constructed InventoryChange object
     */
    InventoryChange getInventoryChange();
}
