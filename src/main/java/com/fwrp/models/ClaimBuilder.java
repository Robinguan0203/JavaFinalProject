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
public class ClaimBuilder implements IInventoryChangeBuilder {
    private Claim claim;

    public ClaimBuilder(){
        claim = new Claim();
    }

    @Override
    public IInventoryChangeBuilder setFood(Food food) {
        claim.setFood(food);
        return this;
    }

    @Override
    public IInventoryChangeBuilder setUser(User user) {
        claim.setCharity((Charity) user);
        return this;
    }

    @Override
    public IInventoryChangeBuilder setDate(Date date) {
        claim.setDate(date);
        return this;
    }


    @Override
    public IInventoryChangeBuilder setQtyDonation(int qtyDonation) {
        claim.setQtyDonation(qtyDonation);
        return this;
    }
    
    @Override
    public IInventoryChangeBuilder setQtyNormal(int qtyNormal) {
        return this;
    }

    @Override
    public IInventoryChangeBuilder setQtyDiscount(int qtyDiscount) {
        return this;
    }
    
    @Override
    public InventoryChange getInventoryChange() {
        return claim;
    }
    
}