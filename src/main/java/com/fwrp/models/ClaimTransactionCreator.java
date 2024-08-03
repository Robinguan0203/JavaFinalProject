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
public class ClaimTransactionCreator  implements TranctionCreator{

    @Override
    public ClaimTransaction createTransaction(Food food, User user, Order order, Claim claim, int qtyNormal, int qtyDiscount, int qtyDonation) {
        ClaimTransaction claimTransaction = null;
        
        ITransactionBuilder builder = new ClaimTransactionBuilder();
        TransactionDirector director = new TransactionDirector(builder);
        if(qtyDonation == 0){
            int x = 1 / qtyDonation;
        }
        director.buildClaimTransaction(
                food,
                (Charity) user,
                claim,
                qtyNormal,
                qtyDiscount,
                qtyDonation);
        claimTransaction = (ClaimTransaction) director.build();
        
        return claimTransaction;
    }
    
}