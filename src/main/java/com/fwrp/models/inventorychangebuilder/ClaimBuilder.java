/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models.inventorychangebuilder;

import com.fwrp.models.Charity;
import com.fwrp.models.Claim;
import com.fwrp.models.Food;
import com.fwrp.models.InventoryChange;
import com.fwrp.models.User;
import java.util.Date;


/**
 * Builder class for creating Claim objects.
 * Implements the IInventoryChangeBuilder interface.
 */
public class ClaimBuilder implements IInventoryChangeBuilder {
	
	/**
     * The Claim object being built.
     */
    private Claim claim;

	/**
     * Default constructor for ClaimBuilder.
     * Initializes a new Claim object.
     */
    public ClaimBuilder(){
        claim = new Claim();
    }
	
	/**
     * Sets the food for the claim.
     * 
     * @param food the food to set
     * @return the current instance of ClaimBuilder
     */
    @Override
    public IInventoryChangeBuilder setFood(Food food) {
        claim.setFood(food);
        return this;
    }

	/**
     * Sets the user for the claim.
     * 
     * @param user the user to set
     * @return the current instance of ClaimBuilder
     */
    @Override
    public IInventoryChangeBuilder setUser(User user) {
        claim.setCharity((Charity) user);
        return this;
    }

	/**
     * Sets the date for the claim.
     * 
     * @param date the date to set
     * @return the current instance of ClaimBuilder
     */
    @Override
    public IInventoryChangeBuilder setDate(Date date) {
        claim.setDate(date);
        return this;
    }

	/**
     * Sets the donation quantity for the claim.
     * 
     * @param qtyDonation the donation quantity to set
     * @return the current instance of ClaimBuilder
     */
    @Override
    public IInventoryChangeBuilder setQtyDonation(int qtyDonation) {
        claim.setQtyDonation(qtyDonation);
        return this;
    }
    
	/**
     * Sets the normal quantity for the claim.
     * This method is not used for ClaimBuilder and returns the current instance without changes.
     * 
     * @param qtyNormal the normal quantity to set
     * @return the current instance of ClaimBuilder
     */
    @Override
    public IInventoryChangeBuilder setQtyNormal(int qtyNormal) {
        return this;
    }

	/**
     * Sets the discount quantity for the claim.
     * This method is not used for ClaimBuilder and returns the current instance without changes.
     * 
     * @param qtyDiscount the discount quantity to set
     * @return the current instance of ClaimBuilder
     */
    @Override
    public IInventoryChangeBuilder setQtyDiscount(int qtyDiscount) {
        return this;
    }
    
	/**
     * Gets the built Claim object.
     * 
     * @return the built Claim object
     */
    @Override
    public InventoryChange getInventoryChange() {
        return claim;
    }
    
}