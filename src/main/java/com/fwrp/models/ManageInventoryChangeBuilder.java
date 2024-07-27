/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models;

import java.util.Date;

/**
 *
 * @author robin
 */
public class ManageInventoryChangeBuilder implements IInventoryChangeBuilder {
    private ManageInventoryChange manageInventoryChange;

    public ManageInventoryChangeBuilder(){
        manageInventoryChange = new ManageInventoryChange();
    }

    @Override
    public IInventoryChangeBuilder setFood(Food food) {
        manageInventoryChange.setFood(food);
        return this;
    }

    @Override
    public IInventoryChangeBuilder setUser(User user) {
        manageInventoryChange.setRetailer((Retailer) user);
        return this;
    }

    @Override
    public IInventoryChangeBuilder setDate(Date date) {
        manageInventoryChange.setDate(date);
        return this;
    }

    @Override
    public IInventoryChangeBuilder setQtyNormal(int qtyNormal) {
        manageInventoryChange.setQtyNormal(qtyNormal);
        return this;
    }

    @Override
    public IInventoryChangeBuilder setQtyDiscount(int qtyDiscount) {
        manageInventoryChange.setQtyDiscount(qtyDiscount);
        return this;
    }

    @Override
    public IInventoryChangeBuilder setQtyDonation(int qtyDonation) {
        manageInventoryChange.setQtyDonation(qtyDonation);
        return this;
    }

    @Override
    public InventoryChange getInventoryChange() {
        return manageInventoryChange;
    }
    
    
}
