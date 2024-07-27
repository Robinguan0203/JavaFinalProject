/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models;

import com.fwrp.constants.TransactionTypeConstant;
import java.util.Date;

/**
 *
 * @author robin
 */
public class TransactionDirector {
    public ITransactionBuilder builder;
    public TransactionDirector(ITransactionBuilder builder){
        this.builder = builder;
    }

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
    
    public void buildOrderTransaction(Food food, Consumer consumer, Order order, int qtyDiscount){
        Date currentDate = new Date();
        int type = TransactionTypeConstant.ORDER;
        
        builder.setFood(food)
                .setUser(consumer)
                .setOrder(order)
                .setDate(currentDate)
                .setType(type)
                .setQtyDiscount(qtyDiscount);
                
    }
    
    public void buildClaimTransaction(Food food, Charity charity, Claim claim, int qtyDonation){
        Date currentDate = new Date();
        int type = TransactionTypeConstant.CLAIM;
        
        builder.setFood(food)
                .setUser(charity)
                .setClaim(claim)
                .setDate(currentDate)
                .setType(type)
                .setQtyDiscount(qtyDonation);
    }

    public Transaction build(){
        return builder.getTransaction();
    }
}

