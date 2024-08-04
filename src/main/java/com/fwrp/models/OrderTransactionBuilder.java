/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models;

import java.util.Date;

/**
 *
 * @author Ke Yan
 */
public class OrderTransactionBuilder implements ITransactionBuilder{
    private OrderTransaction orderTransaction;
    
    public OrderTransactionBuilder(){
        orderTransaction = new OrderTransaction();
    }
    
    public ITransactionBuilder setFood(Food food) {
        orderTransaction.setFood(food);
        return this;
    }

    public ITransactionBuilder setUser(User user) {
        orderTransaction.setUser(user);
        return this;
    }

    public ITransactionBuilder setClaim(Claim claim) {
        return this;
    }

    public ITransactionBuilder setOrder(Order order) {
        orderTransaction.setOrder(order);
        return this;
    }

    public ITransactionBuilder setDate(Date date) {
        orderTransaction.setDate(date);
        return this;
    }

    public ITransactionBuilder setType(int type) {
        orderTransaction.setType(type);
        return this;
    }

    public ITransactionBuilder setQtyNormal(int qtyNormal) {
        orderTransaction.setQtyNormal(qtyNormal);
        return this;
    }

    public ITransactionBuilder setQtyDiscount(int qtyDiscount) {
        orderTransaction.setQtyDiscount(qtyDiscount);
        return this;
    }

    public ITransactionBuilder setQtyDonation(int qtyDonation) {
        orderTransaction.setQtyDonation(qtyDonation);
        return this;
    }

    public Transaction getTransaction() {
        return orderTransaction;
    }
    
}
