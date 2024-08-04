/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.models;

/**
 * Interface representing an inventory change.
 * Provides a method to create a transaction.
 * 
 * @author Robin Guan
 */
public interface InventoryChange {
	
	/**
     * Creates a transaction.
     * 
     * @return a new Transaction object.
     */
    Transaction createTransaction();
}
