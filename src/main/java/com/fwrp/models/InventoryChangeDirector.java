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
public class InventoryChangeDirector {
    
    public IInventoryChangeBuilder builder;
    
    public InventoryChangeDirector(IInventoryChangeBuilder builder){
        this.builder = builder;
    }
    
    public void buildManageInventoryChange(Food food, Retailer retailer, int qtyNormal, int qtyDiscount, int qtyDonation){
        Date currentDate = new Date();
        builder.setFood(food)
                .setUser(retailer)
                .setDate(currentDate)
                .setQtyNormal(qtyNormal)
                .setQtyDiscount(qtyDiscount)
                .setQtyDonation(qtyDonation);
                
    }
    
    public void buildClaim(Food food, Charity charity,int qtyDonation){
        Date currentDate = new Date();
        builder.setFood(food)
                .setUser(charity)
                .setDate(currentDate)
                .setQtyDonation(qtyDonation);
                
    }
    
    public void buildOrder(Food food, Consumer consumer,int qtyDiscount){
        Date currentDate = new Date();
        builder.setFood(food)
                .setUser(consumer)
                .setDate(currentDate)
                .setQtyDiscount(qtyDiscount);
                
    }
    
    public InventoryChange build(){
        return builder.getInventoryChange();
    }
    
}
