package com.fwrp.models;

import java.util.Date;


/**
 *
 * @author robin
 */
public class OrderBuilder implements IInventoryChangeBuilder {
    private Order order;

    public OrderBuilder(){
        order = new Order();
    }

    @Override
    public IInventoryChangeBuilder setFood(Food food) {
        order.setFood(food);
        return this;
    }

    @Override
    public IInventoryChangeBuilder setUser(User user) {
        order.setConsumer((Consumer) user);
        return this;
    }

    @Override
    public IInventoryChangeBuilder setDate(Date date) {
        order.setDate(date);
        return this;
    }


    @Override
    public IInventoryChangeBuilder setQtyDiscount(int qtyDiscount) {
        order.setQtyDiscount(qtyDiscount);
        return this;
    }
    
    @Override
    public IInventoryChangeBuilder setQtyNormal(int qtyNormal) {
        return this;
    }

    @Override
    public IInventoryChangeBuilder setQtyDonation(int qtyDonation) {
        return this;
    }
    
    @Override
    public InventoryChange getInventoryChange() {
        return order;
    }
    
}
