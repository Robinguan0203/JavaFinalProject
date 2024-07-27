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
public interface IInventoryChangeBuilder {
    IInventoryChangeBuilder setFood(Food food);
    IInventoryChangeBuilder setUser(User user);
    IInventoryChangeBuilder setDate(Date date);
    IInventoryChangeBuilder setQtyNormal(int qtyNormal);
    IInventoryChangeBuilder setQtyDiscount(int qtyDiscount);
    IInventoryChangeBuilder setQtyDonation(int qtyDonation);
    InventoryChange getInventoryChange();
}
