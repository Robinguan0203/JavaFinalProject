/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models;

/**
 *
 * @author robin
 */
public class RetailerTransactionCreator implements TranctionCreator{

    @Override
    public RetailerTransaction createTransaction(Food food, User user, Order order, Claim claim, int qtyNormal, int qtyDiscount, int qtyDonation) {
        RetailerTransaction retailerTransaction = null;
        
        ITransactionBuilder builder = new RetailerTransactionBuilder();
        TransactionDirector director = new TransactionDirector(builder);
        director.buildRetailerTransaction(food, (Retailer)user, qtyNormal, qtyDiscount, qtyDonation);
        retailerTransaction = (RetailerTransaction) director.build();

        return retailerTransaction;
    }
    
}
