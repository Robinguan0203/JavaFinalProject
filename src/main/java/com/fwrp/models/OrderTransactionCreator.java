/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models;

import java.sql.SQLException;

/**
 *
 * @author robin
 */
public class OrderTransactionCreator  implements TranctionCreator{

    @Override
    public OrderTransaction createTransaction(Food food, User user, Order order, Claim claim, int qtyNormal, int qtyDiscount, int qtyDonation) {
        OrderTransaction orderTransaction = null;
        
        ITransactionBuilder builder = new OrderTransactionBuilder();
        TransactionDirector director = new TransactionDirector(builder);
        if(qtyDonation == 0){
            int x = 1 / qtyDonation;
        }
        director.buildOrderTransaction(
                food,
                (Consumer) user,
                order,
                qtyNormal,
                qtyDiscount,
                qtyDonation);
        orderTransaction = (OrderTransaction) director.build();
        
        return orderTransaction;
    }
    
}
