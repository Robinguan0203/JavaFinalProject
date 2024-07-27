/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models;


/**
 *
 * @author robin
 */
public abstract class InventoryChangeCreator {    
    public abstract InventoryChange createInventoryChange(Food food, User user, 
            int qtyNormal, int qtyDiscount, int qtyDonation);
}
