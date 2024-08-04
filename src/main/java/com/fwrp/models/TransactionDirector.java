/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models;

import com.fwrp.constants.TransactionTypeConstant;
import java.util.Date;

/**
 * Director class for building different types of transactions.
 * 
 * This class uses the builder pattern to construct various types of transactions.
 * 
 * @autor Robin Guan
 */
public class TransactionDirector {
    public ITransactionBuilder builder;
	
	/**
     * Constructor for TransactionDirector.
     * 
     * @param builder the transaction builder
     */
    public TransactionDirector(ITransactionBuilder builder){
        this.builder = builder;
    }

	/**
     * Builds a retailer transaction.
     * 
     * @param food the food item
     * @param retailer the retailer
     * @param qtyNormal the quantity of normal items
     * @param qtyDiscount the quantity of discounted items
     * @param qtyDonation the quantity of donated items
     */
    public void buildRetailerTransaction(Food food, Retailer retailer,int qtyNormal, int qtyDiscount, int qtyDonation){
        Date currentDate = new Date();
        int type = TransactionTypeConstant.INVENTORY_CHANGE;

        builder.setFood(food)
                .setUser(retailer)
                .setDate(currentDate)
                .setType(type)
                .setQtyNormal(qtyNormal)
                .setQtyDiscount(qtyDiscount)
                .setQtyDonation(qtyDonation);
    }
    
	 /**
     * Builds an order transaction.
     * 
     * @param food the food item
     * @param consumer the consumer
     * @param order the order
     * @param qtyNormal the quantity of normal items
     * @param qtyDiscount the quantity of discounted items
     * @param qtyDonation the quantity of donated items
     */
    public void buildOrderTransaction(Food food, Consumer consumer, Order order, int qtyNormal, int qtyDiscount, int qtyDonation){
        Date currentDate = new Date();
        int type = TransactionTypeConstant.ORDER;
        
        builder.setFood(food)
                .setUser(consumer)
                .setOrder(order)
                .setDate(currentDate)
                .setType(type)
                .setQtyNormal(qtyNormal)
                .setQtyDiscount(qtyDiscount)
                .setQtyDonation(qtyDonation);
                
    }
    
	/**
     * Builds a claim transaction.
     * 
     * @param food the food item
     * @param charity the charity
     * @param claim the claim
     * @param qtyNormal the quantity of normal items
     * @param qtyDiscount the quantity of discounted items
     * @param qtyDonation the quantity of donated items
     */
    public void buildClaimTransaction(Food food, Charity charity, Claim claim, int qtyNormal, int qtyDiscount, int qtyDonation){
        Date currentDate = new Date();
        int type = TransactionTypeConstant.CLAIM;
        builder.setFood(food)
                .setUser(charity)
                .setClaim(claim)
                .setDate(currentDate)
                .setType(type)
                .setQtyNormal(qtyNormal)
                .setQtyDiscount(qtyDiscount)
                .setQtyDonation(qtyDonation);
    }

	/**
     * Builds and returns the transaction.
     * 
     * @return the built transaction
     */
    public Transaction build(){
        return builder.getTransaction();
    }
}

