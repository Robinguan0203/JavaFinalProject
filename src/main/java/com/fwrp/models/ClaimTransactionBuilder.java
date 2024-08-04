/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models;

import java.util.Date;

/**
 * Builder class for creating ClaimTransaction objects.
 * Implements the ITransactionBuilder interface.
 */
public class ClaimTransactionBuilder implements ITransactionBuilder{
	
	/**
     * The ClaimTransaction object being built.
     */
    private ClaimTransaction claimTransaction;
    
	/**
     * Default constructor for ClaimTransactionBuilder.
     * Initializes a new ClaimTransaction object.
     */
    public ClaimTransactionBuilder(){
        claimTransaction = new ClaimTransaction();
    }
    
	/**
     * Sets the food for the claim transaction.
     * 
     * @param food the food to set
     * @return the current instance of ClaimTransactionBuilder
     */
    public ITransactionBuilder setFood(Food food) {
        claimTransaction.setFood(food);
        return this;
    }

	/**
     * Sets the user for the claim transaction.
     * 
     * @param user the user to set
     * @return the current instance of ClaimTransactionBuilder
     */
    public ITransactionBuilder setUser(User user) {
        claimTransaction.setUser(user);
        return this;
    }
	
	/**
     * Sets the order for the claim transaction.
     * This method is not used for ClaimTransactionBuilder and returns the current instance without changes.
     * 
     * @param order the order to set
     * @return the current instance of ClaimTransactionBuilder
     */
    public ITransactionBuilder setOrder(Order order) {
        return this;
    }
	
	/**
     * Sets the claim for the claim transaction.
     * 
     * @param claim the claim to set
     * @return the current instance of ClaimTransactionBuilder
     */
    public ITransactionBuilder setClaim(Claim claim) {
        claimTransaction.setClaim(claim);
        return this;
    }
	
	/**
     * Sets the date for the claim transaction.
     * 
     * @param date the date to set
     * @return the current instance of ClaimTransactionBuilder
     */
    public ITransactionBuilder setDate(Date date) {
        claimTransaction.setDate(date);
        return this;
    }
	
	/**
     * Sets the type for the claim transaction.
     * 
     * @param type the type to set
     * @return the current instance of ClaimTransactionBuilder
     */
    public ITransactionBuilder setType(int type) {
        claimTransaction.setType(type);
        return this;
    }

	 /**
     * Sets the normal quantity for the claim transaction.
     * 
     * @param qtyNormal the normal quantity to set
     * @return the current instance of ClaimTransactionBuilder
     */
    public ITransactionBuilder setQtyNormal(int qtyNormal) {
        claimTransaction.setQtyNormal(qtyNormal);
        return this;
    }

	/**
     * Sets the discount quantity for the claim transaction.
     * 
     * @param qtyDiscount the discount quantity to set
     * @return the current instance of ClaimTransactionBuilder
     */
    public ITransactionBuilder setQtyDiscount(int qtyDiscount) {
        claimTransaction.setQtyDiscount(qtyDiscount);
        return this;
    }

	/**
     * Sets the donation quantity for the claim transaction.
     * 
     * @param qtyDonation the donation quantity to set
     * @return the current instance of ClaimTransactionBuilder
     */
    public ITransactionBuilder setQtyDonation(int qtyDonation) {
        claimTransaction.setQtyDonation(qtyDonation);
        return this;
    }
	
	/**
     * Gets the built ClaimTransaction object.
     * 
     * @return the built ClaimTransaction object
     */
    public Transaction getTransaction() {
        return claimTransaction;
    }
    
}