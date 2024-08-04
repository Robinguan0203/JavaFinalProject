package com.fwrp.models;

import java.util.Date;



/**
 * Concrete builder class for creating Order objects.
 * Implements the IInventoryChangeBuilder interface.
 * 
 * This class is responsible for setting the properties of an Order object.
 * It uses method chaining to set the properties and returns the builder instance.
 * 
 * The class provides methods to set the food, user, date, quantity of discounted items,
 * quantity of normal items, and quantity of donated items for the order.
 * 
 * The getInventoryChange method returns the constructed Order object.
 * 
 */
public class OrderBuilder implements IInventoryChangeBuilder {
    private Order order;

	/**
     * Constructor to initialize the Order object.
     */
    public OrderBuilder(){
        order = new Order();
    }

	/**
     * Sets the food item for the order.
     * 
     * @param food the food item
     * @return the builder instance
     */
    @Override
    public IInventoryChangeBuilder setFood(Food food) {
        order.setFood(food);
        return this;
    }
	
	/**
     * Sets the user (consumer) for the order.
     * 
     * @param user the user (consumer)
     * @return the builder instance
     */
    @Override
    public IInventoryChangeBuilder setUser(User user) {
        order.setConsumer((Consumer) user);
        return this;
    }

	/**
     * Sets the date for the order.
     * 
     * @param date the date
     * @return the builder instance
     */
    @Override
    public IInventoryChangeBuilder setDate(Date date) {
        order.setDate(date);
        return this;
    }

	/**
     * Sets the quantity of discounted items for the order.
     * 
     * @param qtyDiscount the quantity of discounted items
     * @return the builder instance
     */
    @Override
    public IInventoryChangeBuilder setQtyDiscount(int qtyDiscount) {
        order.setQtyDiscount(qtyDiscount);
        return this;
    }
    
	/**
     * Sets the quantity of normal items for the order.
     * 
     * @param qtyNormal the quantity of normal items
     * @return the builder instance
     */
    @Override
    public IInventoryChangeBuilder setQtyNormal(int qtyNormal) {
        return this;
    }
	
	/**
     * Sets the quantity of donated items for the order.
     * 
     * @param qtyDonation the quantity of donated items
     * @return the builder instance
     */
    @Override
    public IInventoryChangeBuilder setQtyDonation(int qtyDonation) {
        return this;
    }
    
	/**
     * Returns the constructed Order object.
     * 
     * @return the constructed Order object
     */
    @Override
    public InventoryChange getInventoryChange() {
        return order;
    }
    
}
