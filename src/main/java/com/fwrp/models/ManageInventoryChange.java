/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models;

import java.util.Date;


public class ManageInventoryChange implements InventoryChange{
    private Food food;
    private Retailer retailer;
    private Date date;
    private int qtyNormal;
    private int qtyDiscount;
    private int qtyDonation;

    public Food getFood() {
        return food;
    }

    public Retailer getRetailer() {
        return retailer;
    }

    public Date getDate() {
        return date;
    }

    public int getQtyNormal() {
        return qtyNormal;
    }

    public int getQtyDiscount() {
        return qtyDiscount;
    }

    public int getQtyDonation() {
        return qtyDonation;
    }

    
    public void setFood(Food food) {
        this.food = food;
    }

    public void setRetailer(Retailer retailer) {
        this.retailer = retailer;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setQtyNormal(int qtyNormal) {
        this.qtyNormal = qtyNormal;
    }

    public void setQtyDiscount(int qtyDiscount) {
        this.qtyDiscount = qtyDiscount;
    }

    public void setQtyDonation(int qtyDonation) {
        this.qtyDonation = qtyDonation;
    }

    
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
