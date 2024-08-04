/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models;

/**
 * Class responsible for creating Claim objects.
 * Extends the InventoryChangeCreator class.
 * Provides a method to create a Claim with specified parameters.
 */
public class ClaimCreator extends InventoryChangeCreator{

	/**
     * Creates a Claim object with the specified parameters.
     * 
     * @param food the food associated with the claim
     * @param user the user (charity) making the claim
     * @param qtyNormal the normal quantity (not used in Claim)
     * @param qtyDiscount the discount quantity (not used in Claim)
     * @param qtyDonation the donation quantity for the claim
     * @return the created Claim object
     */
    @Override
    public Claim createInventoryChange(Food food, User user, 
           int qtyNormal, int qtyDiscount, int qtyDonation) {

        Claim claim = null;

        IInventoryChangeBuilder builder = new ClaimBuilder();
        InventoryChangeDirector director = new InventoryChangeDirector(builder);
        director.buildClaim(food, (Charity)user, qtyDonation);
        claim = (Claim) director.build();
        
        return claim;
    }
    
}
