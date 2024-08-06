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

/**
 * Interface for creating transactions.
 * 
 * This interface defines a method for creating a Transaction object with the specified parameters.
 * 
 * @autor Robin Guan
 */
public interface TranctionCreator {
	
	/**
     * Creates a Transaction object with the specified parameters.
     * 
     * @param food the food item
     * @param user the user
     * @param order the order
     * @param claim the claim
     * @param qtyNormal the quantity of normal items
     * @param qtyDiscount the quantity of discounted items
     * @param qtyDonation the quantity of donated items
     * @return the created Transaction object
     */
    public abstract Transaction createTransaction(Food food, User user, 
            Order order, Claim claim, int qtyNormal, 
            int qtyDiscount, int qtyDonation);
}
