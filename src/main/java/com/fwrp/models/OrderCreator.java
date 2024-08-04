/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models;

/**
 * Concrete creator class for creating Order objects.
 * Extends the InventoryChangeCreator abstract class.
 * 
 * This class is responsible for creating Order objects using the builder pattern.
 * It overrides the createInventoryChange method to build an Order object with the specified parameters.
 * 
 * The method uses an OrderBuilder and InventoryChangeDirector to construct the Order object.
 */
public class OrderCreator extends InventoryChangeCreator{

	/**
     * Creates an Order object with the specified parameters.
     * 
     * @param food the food item
     * @param user the user (consumer)
     * @param qtyNormal the quantity of normal items (not used in Order)
     * @param qtyDiscount the quantity of discounted items
     * @param qtyDonation the quantity of donated items (not used in Order)
     * @return the created Order object
     */
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
