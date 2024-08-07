package com.fwrp.models.transactionbuilder;

import com.fwrp.models.Claim;
import com.fwrp.models.Consumer;
import com.fwrp.models.Food;
import com.fwrp.models.Order;
import com.fwrp.models.OrderTransaction;
import com.fwrp.models.User;

/**
 * Concrete creator class for creating OrderTransaction objects.
 * Implements the TranctionCreator interface.
 * 
 * This class is responsible for creating OrderTransaction objects using the builder pattern.
 * It overrides the createTransaction method to build an OrderTransaction object with the specified parameters.
 * 
 * The method uses an OrderTransactionBuilder and TransactionDirector to construct the OrderTransaction object.
 * 
 * @version 2.0
 * @author Ke Yan
 */
public class OrderTransactionCreator implements TranctionCreator {

    /**
     * Creates an OrderTransaction object with the specified parameters.
     * 
     * @param food the food item
     * @param user the user (consumer)
     * @param order the order
     * @param claim the claim (not used in OrderTransaction)
     * @param qtyNormal the quantity of normal items
     * @param qtyDiscount the quantity of discounted items
     * @param qtyDonation the quantity of donated items
     * @return the created OrderTransaction object
     */
    @Override
    public OrderTransaction createTransaction(Food food, User user, Order order, Claim claim, int qtyNormal, int qtyDiscount, int qtyDonation) {
        OrderTransaction orderTransaction = null;

        ITransactionBuilder builder = new OrderTransactionBuilder();
        TransactionDirector director = new TransactionDirector(builder);

        director.buildOrderTransaction(
                food,
                (Consumer) user,
                order,
                qtyNormal,
                qtyDiscount,
                qtyDonation);
        orderTransaction = (OrderTransaction) director.build();

        return orderTransaction;
    }
}
