/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models;

import com.fwrp.models.transactionbuilder.RetailerTransactionCreator;
import java.util.Date;

/**
 * Manages inventory changes.
 * This class implements the InventoryChange interface and includes properties and methods related to managing inventory changes.
 */
public class ManageInventoryChange implements InventoryChange{
	
	/**
     * The food item associated with the inventory change.
     */
    private Food food;
	
	/**
     * The retailer associated with the inventory change.
     */
    private Retailer retailer;
	
	/**
     * The date of the inventory change.
     */
    private Date date;
	
	/**
     * The normal quantity for the inventory change.
     */
    private int qtyNormal;
	
	/**
     * The discount quantity for the inventory change.
     */
    private int qtyDiscount;
	
	/**
     * The donation quantity for the inventory change.
     */
    private int qtyDonation;

	/**
     * Gets the food item associated with the inventory change.
     * 
     * @return the food item.
     */
    public Food getFood() {
        return food;
    }

	/**
     * Gets the retailer associated with the inventory change.
     * 
     * @return the retailer.
     */
    public Retailer getRetailer() {
        return retailer;
    }

	/**
     * Gets the date of the inventory change.
     * 
     * @return the date.
     */
    public Date getDate() {
        return date;
    }
	
	/**
     * Gets the normal quantity for the inventory change.
     * 
     * @return the normal quantity.
     */
    public int getQtyNormal() {
        return qtyNormal;
    }

	/**
     * Gets the discount quantity for the inventory change.
     * 
     * @return the discount quantity.
     */
    public int getQtyDiscount() {
        return qtyDiscount;
    }
	
	/**
     * Gets the donation quantity for the inventory change.
     * 
     * @return the donation quantity.
     */
    public int getQtyDonation() {
        return qtyDonation;
    }

    /**
     * Sets the food item associated with the inventory change.
     * 
     * @param food the food item.
     */
    public void setFood(Food food) {
        this.food = food;
    }
	
	/**
     * Sets the retailer associated with the inventory change.
     * 
     * @param retailer the retailer.
     */
    public void setRetailer(Retailer retailer) {
        this.retailer = retailer;
    }
	
	/**
     * Sets the date of the inventory change.
     * 
     * @param date the date.
     */
    public void setDate(Date date) {
        this.date = date;
    }
	
	/**
     * Sets the normal quantity for the inventory change.
     * 
     * @param qtyNormal the normal quantity.
     */
    public void setQtyNormal(int qtyNormal) {
        this.qtyNormal = qtyNormal;
    }
	
	/**
     * Sets the discount quantity for the inventory change.
     * 
     * @param qtyDiscount the discount quantity.
     */
    public void setQtyDiscount(int qtyDiscount) {
        this.qtyDiscount = qtyDiscount;
    }
	
	/**
     * Sets the donation quantity for the inventory change.
     * 
     * @param qtyDonation the donation quantity.
     */
    public void setQtyDonation(int qtyDonation) {
        this.qtyDonation = qtyDonation;
    }

    /**
     * Creates a transaction for the inventory change.
     * 
     * @return a new RetailerTransaction object.
     */
    public RetailerTransaction createTransaction() {
        RetailerTransaction transaction = null;
        Order order = null;
        Claim claim = null;
        RetailerTransactionCreator creator = new RetailerTransactionCreator();
        transaction = creator.createTransaction(
                this.getFood(), 
                this.getRetailer(), 
                order, 
                claim, 
                this.getQtyNormal(), 
                this.getQtyDiscount(), 
                this.getQtyDonation()
        );
        
        return transaction;
    }
}
