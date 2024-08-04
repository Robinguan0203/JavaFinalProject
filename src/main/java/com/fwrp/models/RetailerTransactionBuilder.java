/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models;

import java.util.Date;

/**
 * Builder class for creating RetailerTransaction objects.
 * Implements the ITransactionBuilder interface.
 * 
 * This class is responsible for setting the properties of a RetailerTransaction object.
 * It uses method chaining to set the properties and returns the builder instance.
 * 
 * The class provides methods to set the food, user, claim, date, type, 
 * quantity of normal items, quantity of discounted items, and quantity of donated items for the transaction.
 * 
 * The getTransaction method returns the constructed RetailerTransaction object.
 * 
 * @autor Robin Guan
 */
public class RetailerTransactionBuilder implements ITransactionBuilder{
    private RetailerTransaction retailerTransaction;
    
	/**
     * Constructor to initialize the RetailerTransaction object.
     */
    public RetailerTransactionBuilder(){
        retailerTransaction = new RetailerTransaction();
    }
    
	/**
     * Sets the food item for the transaction.
     * 
     * @param food the food item
     * @return the builder instance
     */
    public ITransactionBuilder setFood(Food food) {
        retailerTransaction.setFood(food);
        return this;
    }

	/**
     * Sets the user for the transaction.
     * 
     * @param user the user
     * @return the builder instance
     */
    public ITransactionBuilder setUser(User user) {
        retailerTransaction.setUser(user);
        return this;
    }

	/**
     * Sets the order for the transaction.
     * 
     * @param order the order
     * @return the builder instance
     */
    public ITransactionBuilder setOrder(Order order) {
        return this;
    }
	
	/**
     * Sets the claim for the transaction.
     * 
     * @param claim the claim
     * @return the builder instance
     */
    public ITransactionBuilder setClaim(Claim claim) {
        return this;
    }
	
	/**
     * Sets the date for the transaction.
     * 
     * @param date the date
     * @return the builder instance
     */
    public ITransactionBuilder setDate(Date date) {
        retailerTransaction.setDate(date);
        return this;
    }
	
	/**
     * Sets the type for the transaction.
     * 
     * @param type the type
     * @return the builder instance
     */
    public ITransactionBuilder setType(int type) {
        retailerTransaction.setType(type);
        return this;
    }
	
	/**
     * Sets the quantity of normal items for the transaction.
     * 
     * @param qtyNormal the quantity of normal items
     * @return the builder instance
     */
    public ITransactionBuilder setQtyNormal(int qtyNormal) {
        retailerTransaction.setQtyNormal(qtyNormal);
        return this;
    }
	
	/**
     * Sets the quantity of discounted items for the transaction.
     * 
     * @param qtyDiscount the quantity of discounted items
     * @return the builder instance
     */
    public ITransactionBuilder setQtyDiscount(int qtyDiscount) {
        retailerTransaction.setQtyDiscount(qtyDiscount);
        return this;
    }

	/**
     * Sets the quantity of donated items for the transaction.
     * 
     * @param qtyDonation the quantity of donated items
     * @return the builder instance
     */
    public ITransactionBuilder setQtyDonation(int qtyDonation) {
        retailerTransaction.setQtyDonation(qtyDonation);
        return this;
    }
	
	/**
     * Returns the constructed RetailerTransaction object.
     * 
     * @return the constructed RetailerTransaction object
     */
    public Transaction getTransaction() {
        return retailerTransaction;
    }
    
}
