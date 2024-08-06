/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models.transactionbuilder;

import com.fwrp.models.Claim;
import com.fwrp.models.Food;
import com.fwrp.models.Order;
import com.fwrp.models.Transaction;
import com.fwrp.models.User;
import java.util.Date;

/**
 * Interface for building Transaction objects using the builder pattern.
 * Provides methods to set various properties of a Transaction.
 * 
 */
public interface ITransactionBuilder {
	
	/**
     * Sets the food item for the transaction.
     * 
     * @param food the food item
     * @return the builder instance
     */
    ITransactionBuilder setFood(Food food);
	
	/**
     * Sets the user for the transaction.
     * 
     * @param user the user
     * @return the builder instance
     */
    ITransactionBuilder setUser(User user);
	
	/**
     * Sets the order for the transaction.
     * 
     * @param order the order
     * @return the builder instance
     */
    ITransactionBuilder setOrder(Order order);
	
	/**
     * Sets the claim for the transaction.
     * 
     * @param claim the claim
     * @return the builder instance
     */
    ITransactionBuilder setClaim(Claim claim);
	
	/**
     * Sets the date for the transaction.
     * 
     * @param date the date
     * @return the builder instance
     */
    ITransactionBuilder setDate(Date date);
	
	/**
     * Sets the type for the transaction.
     * 
     * @param type the type
     * @return the builder instance
     */
    ITransactionBuilder setType(int type);
	
	/**
     * Sets the quantity of normal items for the transaction.
     * 
     * @param qtyNormal the quantity of normal items
     * @return the builder instance
     */
    ITransactionBuilder setQtyNormal(int qtyNormal);
	
	 /**
     * Sets the quantity of discounted items for the transaction.
     * 
     * @param qtyDiscount the quantity of discounted items
     * @return the builder instance
     */
    ITransactionBuilder setQtyDiscount(int qtyDiscount);
	
	/**
     * Sets the quantity of donated items for the transaction.
     * 
     * @param qtyDonation the quantity of donated items
     * @return the builder instance
     */
    ITransactionBuilder setQtyDonation(int qtyDonation);
	
	/**
     * Returns the constructed Transaction object.
     * 
     * @return the constructed Transaction object
     */
    Transaction getTransaction();

}
