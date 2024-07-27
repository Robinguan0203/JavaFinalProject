/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models;

/**
 *
 * @author robin
 */
public class ManageInventoryChangeCreator extends InventoryChangeCreator{

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
