/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models.transactionbuilder;

import com.fwrp.models.Claim;
import com.fwrp.models.Food;
import com.fwrp.models.Order;
import com.fwrp.models.Retailer;
import com.fwrp.models.RetailerTransaction;
import com.fwrp.models.User;
import com.fwrp.models.transactionbuilder.RetailerTransactionBuilder;

/**
 * Concrete creator class for creating RetailerTransaction objects.
 * Implements the TranctionCreator interface.
 * 
 * This class is responsible for creating RetailerTransaction objects using the builder pattern.
 * It overrides the createTransaction method to build a RetailerTransaction object with the specified parameters.
 * 
 * The method uses a RetailerTransactionBuilder and TransactionDirector to construct the RetailerTransaction object.
 * 
 * @autor Robin Guan
 */
public class RetailerTransactionCreator implements TranctionCreator{
	
	/**
     * Creates a RetailerTransaction object with the specified parameters.
     * 
     * @param food the food item
     * @param user the user (retailer)
     * @param order the order (not used in RetailerTransaction)
     * @param claim the claim (not used in RetailerTransaction)
     * @param qtyNormal the quantity of normal items
     * @param qtyDiscount the quantity of discounted items
     * @param qtyDonation the quantity of donated items
     * @return the created RetailerTransaction object
     */
    @Override
    public RetailerTransaction createTransaction(Food food, User user, Order order, Claim claim, int qtyNormal, int qtyDiscount, int qtyDonation) {
        RetailerTransaction retailerTransaction = null;
        
        ITransactionBuilder builder = new RetailerTransactionBuilder();
        TransactionDirector director = new TransactionDirector(builder);
        director.buildRetailerTransaction(food, (Retailer)user, qtyNormal, qtyDiscount, qtyDonation);
        retailerTransaction = (RetailerTransaction) director.build();

        return retailerTransaction;
    }
    
}
