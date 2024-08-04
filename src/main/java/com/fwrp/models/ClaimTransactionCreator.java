/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models;

import java.sql.SQLException;

/**
 * Class responsible for creating ClaimTransaction objects.
 * Implements the TransactionCreator interface.
 */
public class ClaimTransactionCreator  implements TranctionCreator{

	/**
     * Creates a ClaimTransaction object with the specified parameters.
     * 
     * @param food the food associated with the transaction
     * @param user the user (charity) involved in the transaction
     * @param order the order associated with the transaction (not used in ClaimTransaction)
     * @param claim the claim associated with the transaction
     * @param qtyNormal the normal quantity for the transaction
     * @param qtyDiscount the discount quantity for the transaction
     * @param qtyDonation the donation quantity for the transaction
     * @return the created ClaimTransaction object
     */
    @Override
    public ClaimTransaction createTransaction(Food food, User user, Order order, Claim claim, int qtyNormal, int qtyDiscount, int qtyDonation) {
        ClaimTransaction claimTransaction = null;
        
        ITransactionBuilder builder = new ClaimTransactionBuilder();
        TransactionDirector director = new TransactionDirector(builder);
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