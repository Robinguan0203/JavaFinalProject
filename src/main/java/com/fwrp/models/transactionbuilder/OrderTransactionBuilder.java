package com.fwrp.models.transactionbuilder;

import com.fwrp.models.Claim;
import com.fwrp.models.Food;
import com.fwrp.models.Order;
import com.fwrp.models.OrderTransaction;
import com.fwrp.models.Transaction;
import com.fwrp.models.User;
import java.util.Date;

/**
 * Builder class for creating OrderTransaction objects.
 * Implements the ITransactionBuilder interface.
 * 
 * This class is responsible for setting the properties of an OrderTransaction object.
 * It uses method chaining to set the properties and returns the builder instance.
 * 
 * The class provides methods to set the food, user, claim, order, date, type, 
 * quantity of normal items, quantity of discounted items, and quantity of donated items for the transaction.
 * 
 * The getTransaction method returns the constructed OrderTransaction object.
 * 
 * @version 2.0
 * @author Ke Yan
 */
public class OrderTransactionBuilder implements ITransactionBuilder {
    private OrderTransaction orderTransaction;

    /**
     * Constructor to initialize the OrderTransaction object.
     */
    public OrderTransactionBuilder() {
        orderTransaction = new OrderTransaction();
    }

    /**
     * Sets the food item for the transaction.
     * 
     * @param food the food item
     * @return the builder instance
     */
    @Override
    public ITransactionBuilder setFood(Food food) {
        orderTransaction.setFood(food);
        return this;
    }

    /**
     * Sets the user for the transaction.
     * 
     * @param user the user
     * @return the builder instance
     */
    @Override
    public ITransactionBuilder setUser(User user) {
        orderTransaction.setUser(user);
        return this;
    }

    /**
     * Sets the claim for the transaction.
     * 
     * @param claim the claim
     * @return the builder instance
     */
    @Override
    public ITransactionBuilder setClaim(Claim claim) {
        // Not implemented for OrderTransaction
        return this;
    }

    /**
     * Sets the order for the transaction.
     * 
     * @param order the order
     * @return the builder instance
     */
    @Override
    public ITransactionBuilder setOrder(Order order) {
        orderTransaction.setOrder(order);
        return this;
    }

    /**
     * Sets the date for the transaction.
     * 
     * @param date the date
     * @return the builder instance
     */
    @Override
    public ITransactionBuilder setDate(Date date) {
        orderTransaction.setDate(date);
        return this;
    }

    /**
     * Sets the type for the transaction.
     * 
     * @param type the type
     * @return the builder instance
     */
    @Override
    public ITransactionBuilder setType(int type) {
        orderTransaction.setType(type);
        return this;
    }

    /**
     * Sets the quantity of normal items for the transaction.
     * 
     * @param qtyNormal the quantity of normal items
     * @return the builder instance
     */
    @Override
    public ITransactionBuilder setQtyNormal(int qtyNormal) {
        orderTransaction.setQtyNormal(qtyNormal);
        return this;
    }

    /**
     * Sets the quantity of discounted items for the transaction.
     * 
     * @param qtyDiscount the quantity of discounted items
     * @return the builder instance
     */
    @Override
    public ITransactionBuilder setQtyDiscount(int qtyDiscount) {
        orderTransaction.setQtyDiscount(qtyDiscount);
        return this;
    }

    /**
     * Sets the quantity of donated items for the transaction.
     * 
     * @param qtyDonation the quantity of donated items
     * @return the builder instance
     */
    @Override
    public ITransactionBuilder setQtyDonation(int qtyDonation) {
        orderTransaction.setQtyDonation(qtyDonation);
        return this;
    }

    /**
     * Returns the constructed OrderTransaction object.
     * 
     * @return the constructed OrderTransaction object
     */
    @Override
    public Transaction getTransaction() {
        return orderTransaction;
    }
}
