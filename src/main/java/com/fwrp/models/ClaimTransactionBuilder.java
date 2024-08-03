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
public class ClaimTransactionBuilder implements ITransactionBuilder{
    private ClaimTransaction claimTransaction;
    
    public ClaimTransactionBuilder(){
        claimTransaction = new ClaimTransaction();
    }
    
    public ITransactionBuilder setFood(Food food) {
        claimTransaction.setFood(food);
        return this;
    }

    public ITransactionBuilder setUser(User user) {
        claimTransaction.setUser(user);
        return this;
    }

    public ITransactionBuilder setOrder(Order order) {
        return this;
    }

    public ITransactionBuilder setClaim(Claim claim) {
        claimTransaction.setClaim(claim);
        return this;
    }

    public ITransactionBuilder setDate(Date date) {
        claimTransaction.setDate(date);
        return this;
    }

    public ITransactionBuilder setType(int type) {
        claimTransaction.setType(type);
        return this;
    }

    public ITransactionBuilder setQtyNormal(int qtyNormal) {
        claimTransaction.setQtyNormal(qtyNormal);
        return this;
    }

    public ITransactionBuilder setQtyDiscount(int qtyDiscount) {
        claimTransaction.setQtyDiscount(qtyDiscount);
        return this;
    }

    public ITransactionBuilder setQtyDonation(int qtyDonation) {
        claimTransaction.setQtyDonation(qtyDonation);
        return this;
    }

    public Transaction getTransaction() {
        return claimTransaction;
    }
    
}