/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models;

import java.util.Date;

/**
 * Director class for constructing InventoryChange objects using the builder pattern.
 * This class provides methods to build different types of inventory changes.
 * 
 */
public class InventoryChangeDirector {
    
	/**
     * The builder used to construct InventoryChange objects.
     */
    public IInventoryChangeBuilder builder;
    
	/**
     * Constructor to initialize the director with a specific builder.
     * 
     * @param builder the builder to use for constructing InventoryChange objects
     */
    public InventoryChangeDirector(IInventoryChangeBuilder builder){
        this.builder = builder;
    }
    
	/**
     * Builds an inventory change for managing inventory.
     * 
     * @param food the food item associated with the inventory change
     * @param retailer the retailer managing the inventory change
     * @param qtyNormal the quantity of normal items
     * @param qtyDiscount the quantity of discounted items
     * @param qtyDonation the quantity of donated items
     */
    public void buildManageInventoryChange(Food food, Retailer retailer, int qtyNormal, int qtyDiscount, int qtyDonation){
        Date currentDate = new Date();
        builder.setFood(food)
                .setUser(retailer)
                .setDate(currentDate)
                .setQtyNormal(qtyNormal)
                .setQtyDiscount(qtyDiscount)
                .setQtyDonation(qtyDonation);
                
    }
    
	/**
     * Builds an inventory change for claiming donated items.
     * 
     * @param food the food item associated with the inventory change
     * @param charity the charity claiming the donation
     * @param qtyDonation the quantity of donated items
     */
    public void buildClaim(Food food, Charity charity,int qtyDonation){
        Date currentDate = new Date();
        builder.setFood(food)
                .setUser(charity)
                .setDate(currentDate)
                .setQtyDonation(qtyDonation);
                
    }
    
	/**
     * Builds an inventory change for ordering discounted items.
     * 
     * @param food the food item associated with the inventory change
     * @param consumer the consumer ordering the discounted items
     * @param qtyDiscount the quantity of discounted items
     */
    public void buildOrder(Food food, Consumer consumer,int qtyDiscount){
        Date currentDate = new Date();
        builder.setFood(food)
                .setUser(consumer)
                .setDate(currentDate)
                .setQtyDiscount(qtyDiscount);
                
    }
    
	/**
     * Returns the constructed InventoryChange object.
     * 
     * @return the constructed InventoryChange object
     */
    public InventoryChange build(){
        return builder.getInventoryChange();
    }
    
}
