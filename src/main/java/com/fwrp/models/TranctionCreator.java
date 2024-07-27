/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models;

/**
 *
 * @author robin
 */
public interface TranctionCreator {
    public abstract Transaction createTransaction(Food food, User user, 
            Order order, Claim claim, int qtyNormal, 
            int qtyDiscount, int qtyDonation);
}
