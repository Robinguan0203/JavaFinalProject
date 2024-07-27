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
public interface ITransactionBuilder {
    ITransactionBuilder setFood(Food food);
    ITransactionBuilder setUser(User user);
    ITransactionBuilder setOrder(Order order);
    ITransactionBuilder setClaim(Claim claim);
    ITransactionBuilder setDate(Date date);
    ITransactionBuilder setType(int type);
    ITransactionBuilder setQtyNormal(int qtyNormal);
    ITransactionBuilder setQtyDiscount(int qtyDiscount);
    ITransactionBuilder setQtyDonation(int qtyDonation);
    Transaction getTransaction();

}
