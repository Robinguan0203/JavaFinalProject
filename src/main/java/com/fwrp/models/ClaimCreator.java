/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models;

/**
 *
 * @author robin
 */
public class ClaimCreator extends InventoryChangeCreator{

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
