/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models;

import java.util.Date;

/**
 *
 * @author robin
 */
public class RetailerTransactionBuilder implements ITransactionBuilder{
    private RetailerTransaction retailerTransaction;
    
    public RetailerTransactionBuilder(){
        retailerTransaction = new RetailerTransaction();
    }
    
    public ITransactionBuilder setFood(Food food) {
        retailerTransaction.setFood(food);
        return this;
    }

    public ITransactionBuilder setUser(User user) {
        retailerTransaction.setUser(user);
        return this;
    }

    public ITransactionBuilder setOrder(Order order) {
        return this;
    }

    public ITransactionBuilder setClaim(Claim claim) {
        return this;
    }

    public ITransactionBuilder setDate(Date date) {
        retailerTransaction.setDate(date);
        return this;
    }

    public ITransactionBuilder setType(int type) {
        retailerTransaction.setType(type);
        return this;
    }

    public ITransactionBuilder setQtyNormal(int qtyNormal) {
        retailerTransaction.setQtyNormal(qtyNormal);
        return this;
    }

    public ITransactionBuilder setQtyDiscount(int qtyDiscount) {
        retailerTransaction.setQtyDiscount(qtyDiscount);
        return this;
    }

    public ITransactionBuilder setQtyDonation(int qtyDonation) {
        retailerTransaction.setQtyDonation(qtyDonation);
        return this;
    }

    public Transaction getTransaction() {
        return retailerTransaction;
    }
    
}
