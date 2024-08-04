/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models;

/**
 *
 * @author Ke Yan
 */
public class OrderCreator extends InventoryChangeCreator{

    @Override
    public Order createInventoryChange(Food food, User user, 
           int qtyNormal, int qtyDiscount, int qtyDonation) {

        Order order = null;

        IInventoryChangeBuilder builder = new OrderBuilder();
        InventoryChangeDirector director = new InventoryChangeDirector(builder);
        director.buildOrder(food, (Consumer)user, qtyDiscount);
        order = (Order) director.build();
        
        return order;
    }
    
}
